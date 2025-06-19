package com.abv.bookstore.pos.common.service;

import lombok.Getter;

@Getter
public class SearchCriteria {
    private String key;
    private Object value;
    private Object valueTo; // optional, used for BETWEEN
    private SearchOperation operation;

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }
    public SearchCriteria(String key, Object value, Object valueTo, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.valueTo = valueTo;
        this.operation = operation;
    }
}
