package com.abv.bookstore.pos.modules.orders.service;
import com.abv.bookstore.pos.common.domain.StockMovementType;
import com.abv.bookstore.pos.common.exception.APIException;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.common.util.StockTypeReason;
import com.abv.bookstore.pos.modules.book.entity.Book;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import com.abv.bookstore.pos.modules.orders.dt.OrderItemRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderResponseDTO;
import com.abv.bookstore.pos.modules.orders.entity.Order;
import com.abv.bookstore.pos.modules.orders.entity.OrderItem;
import com.abv.bookstore.pos.modules.orders.mapper.OrderMapper;
import com.abv.bookstore.pos.modules.orders.repos.OrderRepository;
import com.abv.bookstore.pos.modules.stock.dto.BookStockUpdateRequest;
import com.abv.bookstore.pos.modules.stock.entity.StockMovement;
import com.abv.bookstore.pos.modules.stock.repo.StockMovementRepository;
import com.abv.bookstore.pos.modules.stock.service.StockService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderRequestDTO, OrderResponseDTO,Long,Order> implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;
//    private final StockMovementRepository stockMovementRepository;
    private final StockService stockService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper mapper,
                            BookRepository bookRepository,
                            StockService stockService) {
        super(orderRepository, mapper, Order.class, OrderResponseDTO.class);
        this.orderRepository = orderRepository;
        this.orderMapper = mapper;
        this.bookRepository = bookRepository;
        this.stockService = stockService;
//        this.stockMovementRepository = stockMovementRepository;
    }

    @Transactional
    @Override
    public OrderResponseDTO create(OrderRequestDTO request) {
        var order = baseMapper.mapToEntity(request);
        BigDecimal subtotal = BigDecimal.ZERO;
        var bookIds = request.orderItems()
                .stream().map(OrderItemRequestDTO::bookId)
                .collect(Collectors.toList());
        Map<Long, Book> bookMap = bookRepository.findAllById(bookIds)
                .stream().collect(Collectors.toMap(Book::getId, Function.identity()));

        // Process order items
        for (OrderItemRequestDTO itemDTO : request.orderItems()) {
            var book = bookMap.get(itemDTO.bookId());

            if(book == null) {
                throw new ResourceNotFoundException("Book not found");
            }
            // check stock-quantity...
            if (!stockService.isStockAvailable(itemDTO.quantity(),book.getId())){
                throw new APIException(HttpStatus.BAD_REQUEST,"Stock Not available");
            }
            // create order Item
            OrderItem orderItem = createOrderItem(order,book,itemDTO);
            order.addOrderItem(orderItem);

            subtotal = subtotal.add(orderItem.getUnitPrice().multiply(BigDecimal.valueOf(itemDTO.quantity())));
//            stockMovementRepository.save(stockMovement);
            stockService.updateBookStock(
                    new BookStockUpdateRequest(
                            book.getId(),
                            StockMovementType.INBOUND,
                            itemDTO.quantity(),
                            StockTypeReason.SOLD_OUT
                            ));
        }

        // Calculate totals
        order.setSubtotal(subtotal);

        BigDecimal taxRate = request.taxRate() != null
                ? request.taxRate():BigDecimal.ZERO;
//                : getDefaultTaxRate();
        order.setTaxRate(taxRate);

        BigDecimal taxAmount = subtotal.multiply(taxRate);
        order.setTaxAmount(taxAmount);

        BigDecimal discountAmount = request.discountAmount() != null
                ? request.discountAmount()
                : BigDecimal.ZERO;
        order.setDiscountAmount(discountAmount);

        BigDecimal totalAmount = subtotal.add(taxAmount).subtract(discountAmount);
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(ZonedDateTime.now());
        order.setUpdatedAt(ZonedDateTime.now());

        Order savedOrder;
        try {
           savedOrder = orderRepository.save(order);

        } catch (Exception ex) {
            ex.printStackTrace(); // Or log the full exception
            throw ex;
        }
        return baseMapper.mapToResponseDTO(savedOrder);
    }


    private OrderItem createOrderItem(Order order, Book book, OrderItemRequestDTO itemDTO) {

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setQuantity(itemDTO.quantity());

        // Use provided unit price or book's current price
        BigDecimal unitPrice = itemDTO.unitPrice() != null
                ? itemDTO.unitPrice()
                : book.getCurrentPrice().get().getPrice();
        orderItem.setUnitPrice(unitPrice);

        // Store snapshots for historical reference
        orderItem.setBookTitle(book.getTitle());
        orderItem.setBookSku(book.getSku());

        return orderItem;
    }


}
