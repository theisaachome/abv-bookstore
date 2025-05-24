package com.abv.bookstore.pos.common.domain;

public enum StockMovementType {
   INBOUND(+1),OUTBOUND(-1);
    private final int direction;

    StockMovementType(int direction) {
        this.direction = direction;
    }
    public int apply(int quantity) {
        return direction * quantity;
    }
}
