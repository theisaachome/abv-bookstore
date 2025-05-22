package com.abv.bookstore.common.service;

public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() { return key; }
    public Object getValue() { return value; }
    public SearchOperation getOperation() { return operation; }
}
