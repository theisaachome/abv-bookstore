package com.abv.bookstore.modules.book.service;
import com.abv.bookstore.common.service.BaseSpecification;
import com.abv.bookstore.common.service.SearchCriteria;
import com.abv.bookstore.common.service.SearchOperation;
import com.abv.bookstore.common.util.StockType;
import com.abv.bookstore.common.domain.Type;
import com.abv.bookstore.common.service.BaseServiceImpl;
import com.abv.bookstore.modules.book.dto.BookFilter;
import com.abv.bookstore.modules.book.dto.BookRequest;
import com.abv.bookstore.modules.book.dto.BookResponse;
import com.abv.bookstore.modules.book.dto.SellerBookDTO;
import com.abv.bookstore.modules.book.entity.Book;
import com.abv.bookstore.modules.book.mapper.BookMapper;
import com.abv.bookstore.modules.book.repo.BookRepository;
import com.abv.bookstore.modules.stocks.entity.StockMovement;
import com.abv.bookstore.modules.stocks.repo.StockMovementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookRequest,BookResponse,Long, Book>
        implements BookService{

    private final StockMovementRepository stockMovementRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository repository, BookMapper mapper, StockMovementRepository stockMovementRepository, BookMapper bookMapper) {
        super(repository,mapper,Book.class,BookResponse.class);
        this.stockMovementRepository = stockMovementRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    @Override
    public BookResponse create(BookRequest request) {
        var bookEntity = baseMapper.mapToEntity(request);

        //  Save book first for ID (if it's a new book)
       var savedEntity=  repository.save(bookEntity);

        // update Stock
        var stockMovement= new StockMovement();
        stockMovement.setBook(savedEntity);
        stockMovement.setType(Type.INBOUND);
        int quantity = request.initialStockQuantity();
        stockMovement.setQuantity(stockMovement.getType().apply(quantity));
        stockMovement.setReason(StockType.INITIAL_STOCK);
        stockMovementRepository.save(stockMovement);

        return baseMapper.mapToResponseDTO(savedEntity);
    }

    @Override
    public List<BookResponse> findAllBook() {
        var books = repository.findAll();
        return books.stream().map(baseMapper::mapToResponseDTO).collect(Collectors.toList());
    }

    @Override
    public Page<BookResponse> searchAndPaginate(BookFilter filter, int page, int size, String sortBy, String sortOrder) {
        Sort sortDir = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sortDir);

        // filter build-up
        BaseSpecification<Book> specification = new BaseSpecification<>();
        if (filter.title() != null && !filter.title().isBlank()) {
            specification.add(new SearchCriteria("title",filter.title(), SearchOperation.LIKE));
        }
        if(filter.sku() != null && !filter.sku().isBlank()){
            specification.add(new SearchCriteria("sku",filter.sku(), SearchOperation.LIKE));
        }
//        if(filter.author() != null && !filter.author().isBlank()){
//            specification.add(new SearchCriteria("author.name",filter.author(), SearchOperation.LIKE));
//        }
        if(filter.author() != null && !filter.author().isBlank()){
            specification.add(new SearchCriteria("author",filter.author(), SearchOperation.LIKE));
        }
        var bookPages =  repository.findAll(specification,pageable);
        return bookPages.map(base -> baseMapper.mapToResponseDTO(base));
    }

    @Override
    public Page<SellerBookDTO> searchBooks(String query, Pageable pageable) {

        // filter build-up
        BaseSpecification<Book> spec = new BaseSpecification<>();
        if(query != null && !query.isBlank()){
            spec.add(new SearchCriteria("title",query, SearchOperation.OR));
            spec.add(new SearchCriteria("sku",query, SearchOperation.OR));
            spec.add(new SearchCriteria("author",query, SearchOperation.OR));
        }

        var bookPages = repository.findAll(spec,pageable);
        return bookPages.map(bookMapper::mapToSellerBookDTO);
    }
}
