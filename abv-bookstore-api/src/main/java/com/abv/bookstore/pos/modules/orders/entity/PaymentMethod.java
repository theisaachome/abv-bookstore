package com.abv.bookstore.pos.modules.orders.entity;

public enum PaymentMethod {
    CASH,
    CREDIT_CARD,
    DEBIT_CARD,
    CHECK,
    STORE_CREDIT,
    GIFT_CARD,
    MIXED // For split payments
}
