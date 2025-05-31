package com.abv.bookstore.pos.modules.orders.entity;

import com.abv.bookstore.pos.common.domain.BaseEntity;
import com.abv.bookstore.pos.modules.book.entity.BookPrice;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name="orders")
public class Order extends BaseEntity {
    @Column(nullable = false, unique = true, length = 50)
    private String orderNumber; // e.g., "ORD-20241124-001"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status; // PENDING, COMPLETED, CANCELLED, REFUNDED

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type; // WALK_IN, ONLINE, PHONE


    @Column(nullable = false)
    private BigDecimal subtotal; // Sum of all line totals before tax/discount


    @Column(precision = 10, scale = 2)
    private BigDecimal discountAmount; // Total discount applied

    @Column(nullable = false, precision = 5, scale = 4)
    private BigDecimal taxRate; // Tax rate applied (e.g., 0.0825 for 8.25%)

    private BigDecimal taxAmount; // Total tax amount
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount; // Final amount (subtotal + tax - discount)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private  PaymentMethod paymentMethod; // CASH, CARD, CHECK, STORE_CREDIT

    @Column(length = 100)
    private String customerName; // For walk-in customers

    @Column(length = 20)
    private String customerPhone;

    @Column(length = 100)
    private String customerEmail;

    @Column(length = 100)
    private String cashierName; // Staff who processed the order

    @Column(columnDefinition = "TEXT")
    private String notes; // Special instructions or notes



    @Column(nullable = false)
    private LocalDateTime orderDate;

    // Add these for easier reporting queries
    @Column(nullable = false)
    private LocalDate orderDateOnly; // For daily/weekly/monthly grouping

    @Column(nullable = false)
    private Integer orderYear;

    @Column(nullable = false)
    private Integer orderMonth;

    @Column(nullable = false)
    private Integer orderWeek; // Week of year (1-53)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true)
    @BatchSize(size = 20)
    private List<OrderItem> orderItems = new ArrayList<>();

    // Calculated field - total quantity of items
    @Transient
    public Integer getTotalQuantity() {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItem.setOrder(null);
        this.orderItems.remove(orderItem);
    }

    public void removeAllOrderItem(){
        Iterator<OrderItem> iterator = this.orderItems.iterator();
        while(iterator.hasNext()){
            OrderItem orderItem = iterator.next();
            orderItem.setOrder(null);
            iterator.remove();
        }
    }
}
