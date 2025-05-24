package com.abv.bookstore.pos.modules.orders.dt;

import com.abv.bookstore.pos.modules.orders.entity.OrderStatus;
import com.abv.bookstore.pos.modules.orders.entity.OrderType;
import com.abv.bookstore.pos.modules.orders.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Long id,
        String orderNumber,
        OrderStatus status,
        OrderType type,
        BigDecimal subtotal,
        BigDecimal taxAmount,
        BigDecimal taxRate,
        BigDecimal discountAmount,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        String customerName,
        String customerPhone,
        String customerEmail,
        String cashierName,
        String notes,
        LocalDateTime orderDate,
        Integer totalQuantity,
        List<OrderItemResponseDTO>items
) {
}
