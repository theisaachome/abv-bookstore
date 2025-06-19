package com.abv.bookstore.pos.modules.stock.service;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.common.service.BaseSpecification;
import com.abv.bookstore.pos.common.service.SearchCriteria;
import com.abv.bookstore.pos.common.service.SearchOperation;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDtoView;
import com.abv.bookstore.pos.modules.stock.dto.StockFilters;
import com.abv.bookstore.pos.modules.stock.dto.StockMovementDto;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import com.abv.bookstore.pos.modules.stock.mapper.StockMovementMapper;
import com.abv.bookstore.pos.modules.stock.repo.StockMovementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class StockServiceImpl extends BaseServiceImpl<StockMovementDto,StockMovementDto,Long,StockMovement> implements StockService{

    private final BookRepository bookRepository;
    private final StockMovementRepository stockMovementRepository;
    private final StockMovementMapper stockMovementMapper;

    public StockServiceImpl(BookRepository bookRepository, StockMovementRepository stockMovementRepository, StockMovementMapper stockMovementMapper) {
        super(stockMovementRepository, stockMovementMapper, StockMovement.class, StockMovementDto.class);
        this.bookRepository = bookRepository;
        this.stockMovementRepository = stockMovementRepository;
        this.stockMovementMapper = stockMovementMapper;
    }
    @Transactional
    @Override
    public StockMovementDto updateBookStock(StockMovementDto dto) {
        var book = bookRepository.findById(dto.bookId())
                .orElseThrow(()->new ResourceNotFoundException("Book not found"));
        var newStock = stockMovementMapper.mapToEntity(dto);
        newStock.setBook(book);
        var saveStock= stockMovementRepository.save(newStock);
        return stockMovementMapper.mapToResponseDTO(saveStock);
    }


    @Override
    public Page<StockMovementDtoView> getStockHistory(StockFilters filters, int page, int size, String sortBy, String sortOrder) {
        Sort sortDir = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sortDir);
        // filters build-up
        BaseSpecification<StockMovement> specification = new BaseSpecification<>();
        if(filters.stockMovementType()!=null){
            specification.add(new SearchCriteria("stock_movement_type",filters.stockMovementType(), SearchOperation.EQUAL));
        }

        LocalDateTime fromDateTime = (filters.fromDate() != null) ? filters.fromDate().atStartOfDay() : null;
        LocalDateTime toDateTime = (filters.toDate() != null) ? filters.toDate().atTime(23, 59, 59, 999999999) : null;

        var stockPages = stockMovementRepository.findAll(specification,pageable);
       return   stockPages.map(data->stockMovementMapper.mapToStockView(data));
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

