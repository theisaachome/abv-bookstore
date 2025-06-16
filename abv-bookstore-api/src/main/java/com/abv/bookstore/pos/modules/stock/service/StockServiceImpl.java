package com.abv.bookstore.pos.modules.stock.service;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import com.abv.bookstore.pos.modules.stock.dto.AddStockReq;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import com.abv.bookstore.pos.modules.stock.mapper.StockMovementMapper;
import com.abv.bookstore.pos.modules.stock.repo.StockMovementRepository;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl extends BaseServiceImpl<BookStockUpdateRequest, AddStockReq,Long, StockMovement>
implements StockService {

    private final BookRepository bookRepository;
    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;

    public StockServiceImpl(BookRepository bookRepository, StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper) {
        super(stockMovementRepository, stockMovementMapper, StockMovement.class, AddStockReq.class);
        this.bookRepository = bookRepository;
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
    }

    @Override
    public AddStockReq updateBookStock(BookStockUpdateRequest bookStockUpdateRequest) {
        var book = bookRepository.findById(bookStockUpdateRequest.bookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found"));
        var newStock = stockMovementMapper.mapToEntity(bookStockUpdateRequest);
        newStock.setBook(book);
       var saveStock= stockMovementRepository.save(newStock);
        return stockMovementMapper.mapToResponseDTO(saveStock);
    }

    @Override
    public boolean isStockAvailable(int requestQuantity, Long bookId) {
        var availableStockNumber = stockMovementRepository.sumStockByBook(bookId);
        if(  availableStockNumber>=requestQuantity) {
            return true;
        }
        return false;
    }
}
