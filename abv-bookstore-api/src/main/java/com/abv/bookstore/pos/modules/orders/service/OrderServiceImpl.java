package com.abv.bookstore.pos.modules.orders.service;
import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.service.BaseServiceImpl;
import com.abv.bookstore.pos.modules.book.entity.Book;
import com.abv.bookstore.pos.modules.book.repo.BookRepository;
import com.abv.bookstore.pos.modules.orders.dt.OrderItemRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderResponseDTO;
import com.abv.bookstore.pos.modules.orders.entity.Order;
import com.abv.bookstore.pos.modules.orders.entity.OrderItem;
import com.abv.bookstore.pos.modules.orders.mapper.OrderMapper;
import com.abv.bookstore.pos.modules.orders.repos.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends BaseServiceImpl<OrderRequestDTO, OrderResponseDTO,Long,Order> implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final BookRepository bookRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper mapper, BookRepository bookRepository) {
        super(orderRepository, mapper, Order.class, OrderResponseDTO.class);
        this.orderRepository = orderRepository;
        this.orderMapper = mapper;
        this.bookRepository = bookRepository;
    }

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
//            var book = bookRepository.findById(itemDTO.bookId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
            var book = bookMap.get(itemDTO.bookId());
            if(book == null) {
                throw new ResourceNotFoundException("Book not found");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setQuantity(itemDTO.quantity());

            // Use provided unit price or book's current price
            BigDecimal unitPrice = itemDTO.unitPrice() != null
                    ? itemDTO.unitPrice():BigDecimal.TEN;
//                    : book.getCurrentPrice().get().getPrice();
            orderItem.setUnitPrice(unitPrice);

            // Store snapshots for historical reference
            orderItem.setBookTitle(book.getTitle());
            orderItem.setBookSku(book.getSku());

            order.getOrderItems().add(orderItem);
            subtotal = subtotal.add(unitPrice.multiply(BigDecimal.valueOf(itemDTO.quantity())));
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

        var savedOrder = repository.save(order);
        return baseMapper.mapToResponseDTO(savedOrder);
    }
}
