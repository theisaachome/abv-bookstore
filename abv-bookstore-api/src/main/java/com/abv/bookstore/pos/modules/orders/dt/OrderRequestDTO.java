package com.abv.bookstore.pos.modules.orders.dt;

import com.abv.bookstore.pos.modules.orders.entity.OrderItem;
import com.abv.bookstore.pos.modules.orders.entity.OrderType;
import com.abv.bookstore.pos.modules.orders.entity.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequestDTO(
        @NotNull
        OrderType orderType,
        @NotNull
        @Valid
        List<OrderItemRequestDTO> orderItems,
        String customerName,
        @Email
        String customerEmail,
//        @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Invalid phone number")
        String customerPhone,
        @NotNull
        PaymentMethod paymentMethod,
        @DecimalMin(value = "0.0", inclusive = true)
        @Digits(integer = 5, fraction = 4)
        BigDecimal taxRate ,// Optional, can use store default

        @DecimalMin(value = "0.0", inclusive = true)
        @Digits(integer = 10, fraction = 2)
         BigDecimal discountAmount,
        String notes,
        @NotBlank
         String cashierName
) {
}
