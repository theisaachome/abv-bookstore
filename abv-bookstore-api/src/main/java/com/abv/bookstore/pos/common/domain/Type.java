package com.abv.bookstore.pos.common.domain;

public enum Type {
   INBOUND(+1),OUTBOUND(-1);
    private final int direction;

    Type(int direction) {
        this.direction = direction;
    }
    public int apply(int quantity) {
        return direction * quantity;
    }
}
