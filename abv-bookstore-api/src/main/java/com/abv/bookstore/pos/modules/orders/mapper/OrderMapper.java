package com.abv.bookstore.pos.modules.orders.mapper;

import com.abv.bookstore.pos.common.exception.ResourceNotFoundException;
import com.abv.bookstore.pos.common.mapper.BaseMapper;
import com.abv.bookstore.pos.common.util.AppCodeGenerator;
import com.abv.bookstore.pos.modules.orders.dt.OrderItemRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderItemResponseDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderResponseDTO;
import com.abv.bookstore.pos.modules.orders.entity.Order;
import com.abv.bookstore.pos.modules.orders.entity.OrderItem;
import com.abv.bookstore.pos.modules.orders.entity.OrderStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.stream.Collectors;

@Component
public class OrderMapper implements BaseMapper<OrderRequestDTO, OrderResponseDTO, Order> {

    @Override
    public Order mapToEntity(OrderRequestDTO request) {
        Order order = new Order();
        order.setOrderNumber(AppCodeGenerator.generateOrderNumber());
        order.setType(request.orderType());
        order.setStatus(OrderStatus.COMPLETED); // walk-in
        order.setCustomerName(request.customerName());
        order.setCustomerEmail(request.customerEmail());
        order.setCustomerPhone(request.customerPhone());
        order.setPaymentMethod(request.paymentMethod());
        order.setCashierName(request.cashierName());
        order.setNotes(request.notes());
        order.setOrderDate(LocalDateTime.now());
        // Set reporting fields
        LocalDate today = LocalDate.now();
        order.setOrderDateOnly(today);
        order.setOrderYear(today.getYear());
        order.setOrderMonth(today.getMonthValue());
        order.setOrderWeek(today.get(WeekFields.ISO.weekOfWeekBasedYear()));

        return order;
    }

    @Override
    public OrderResponseDTO mapToResponseDTO(Order order) {

        var orderItemResponse=order.getOrderItems().stream().map(item -> new OrderItemResponseDTO(
                        item.getId(),
                item.getBook().getId(),
                item.getBookTitle(),
                item.getBookSku(),
                item.getBookSku(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getLineTotal()
                ))
                .collect(Collectors.toList());
        return new OrderResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getType(),
                order.getSubtotal(),
                order.getTaxAmount(),
                order.getTaxRate(),
                order.getDiscountAmount(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerName(),
                order.getCustomerPhone(),
                order.getCustomerEmail(),
                order.getCashierName(),
                order.getNotes(),
                order.getOrderDate(),
                order.getTotalQuantity(),
                orderItemResponse
        );
    }

    @Override
    public void updateEntity(OrderRequestDTO request, Order order) {

    }
}
