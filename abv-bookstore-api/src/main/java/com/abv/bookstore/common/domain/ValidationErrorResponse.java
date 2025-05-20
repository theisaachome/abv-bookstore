package com.abv.bookstore.common.domain;
import com.abv.bookstore.common.exception.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse {
    private List<Map<String, String>> errors;
}
