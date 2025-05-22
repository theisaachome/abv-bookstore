package com.abv.bookstore.common.service;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BaseSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> criteriaList;

    public BaseSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();
        List<Predicate> orPredicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            Path<String> path;

            if (criteria.getKey().contains(".")) {
                String[] parts = criteria.getKey().split("\\.");
                Path<?> nestedPath = root;
                for (String part : parts) {
                    nestedPath = nestedPath.get(part);
                }
                path = (Path<String>) nestedPath.as(String.class);
            } else {
                path = root.get(criteria.getKey());
            }

            switch (criteria.getOperation()) {
                case EQUAL:
                    predicates.add(cb.equal(path, criteria.getValue()));
                    break;
                case LIKE:
                    predicates.add(cb.like(cb.lower(path), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
                case OR:
                    orPredicates.add(cb.like(cb.lower(path), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;
            }
        }

        Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[0]));
        if (!orPredicates.isEmpty()) {
            finalPredicate = cb.and(finalPredicate, cb.or(orPredicates.toArray(new Predicate[0])));
        }

        return finalPredicate;
    }
}
