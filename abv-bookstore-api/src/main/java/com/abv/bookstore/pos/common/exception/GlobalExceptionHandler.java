package com.abv.bookstore.pos.common.exception;

import com.abv.bookstore.pos.common.domain.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setErrorCode("RESOURCE_NOT_FOUND");
        return   ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    // Application API Exception
    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorResponse> handleApplicationAPIException(APIException e, HttpServletRequest request,WebRequest webRequest) {
        var errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setErrorCode(request.getRequestURI());
        errorResponse.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    // global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex,
                                                              WebRequest request){
        ex.printStackTrace(); // Don't skip this!
        var errorDetails = new ErrorResponse();
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setErrorCode("INTERNAL_SERVER_ERROR");
        errorDetails.setPath(request.getContextPath());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request){
        List<Map<String, String>> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage()
                ))
                .toList();


        var response = new ValidationErrorResponse();
        response.setMessage("Validation failed");
        response.setErrorCode("VALIDATION_ERROR");
        response.setErrors(fieldErrors);
        response.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(UnauthorizedException.class)
//    public ResponseEntity<ErrorResponse> handleUnauthorized(UnauthorizedException ex, HttpServletRequest request) {
//        ErrorResponse error = new ErrorResponse();
//        error.setMessage("Unauthorized access");
//        error.setErrorCode("UNAUTHORIZED");
//        error.setPath(request.getRequestURI());
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
//    }


}
