package com.abv.bookstore.pos.modules.book.service;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.service.BaseSpecification;
import com.abv.bookstore.pos.common.service.SearchCriteria;
import com.abv.bookstore.pos.common.service.SearchOperation;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.common.util.StockTypeReason;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.modules.author.entity.Author;
import com.abv.bookstore.pos.modules.book.dto.*;
import com.abv.bookstore.pos.modules.book.entity.Book;
import com.abv.bookstore.pos.modules.book.entity.BookPrice;
import com.abv.bookstore.pos.modules.book.mapper.BookMapper;
import com.abv.bookstore.pos.modules.book.repo.BookPriceRepository;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDto;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import com.abv.bookstore.pos.modules.stock.repo.StockMovementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookReq, BookRes,Long, Book>
        implements BookService{

    private final StockMovementRepository stockMovementRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository repository, BookMapper mapper, StockMovementRepository stockMovementRepository,  BookMapper bookMapper) {
        super(repository,mapper,Book.class, BookRes.class);
        this.stockMovementRepository = stockMovementRepository;
//        this.bookPriceRepository = bookPriceRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional
    @Override
    public BookRes create(BookReq request) {
        var bookEntity = baseMapper.mapToEntity(request);
        //  Save book first for ID (if it's a new book)
       var savedEntity=  repository.save(bookEntity);
       // insert book-author
       createInitialPrice(savedEntity,request.price());
       createInitialStock(savedEntity, request.stockQuantity());
        return baseMapper.mapToResponseDTO(savedEntity);
    }
    @Override
    public Page<BookInventoryDto> searchAndPaginate(BookFilter filter, int page, int size, String sortBy, String sortOrder) {
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
        return bookPages.map(base -> bookMapper.mapToBookInventoryDto((base)));
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


    private void createInitialBookAuthor(Book book){

    }
    private void  createInitialPrice(Book book,BigDecimal price){
        // manage book-prices
        var bookPrice = new BookPrice();
        bookPrice.setPrice(new BigDecimal(String.valueOf(price)));
        bookPrice.setStartDate(LocalDateTime.now(ZoneOffset.UTC));
        bookPrice.setEndDate(null); // no expiry
        bookPrice.setBook(book);
        book.addPrice(bookPrice);
    }
    private void createInitialStock(Book book,Integer quantity){
        // update Stock
        var stockMovement= new StockMovement();
        stockMovement.setBook(book);
        stockMovement.setReference(AppCodeGenerator.generateStockMovementRef());
        stockMovement.setStockMovementType(StockMovementType.INBOUND);
        stockMovement.setQuantity(stockMovement.getStockMovementType().apply(quantity));
        stockMovement.setReason(StockTypeReason.INITIAL_STOCK);
        stockMovement.setPerformedBy(""); // to-do setup from security-context
        stockMovementRepository.save(stockMovement);

    }
}
