package com.vlms.stock.handlers;

import com.vlms.stock.dto.ApiResponseDTO;
import com.vlms.stock.dto.ApiResponseStatus;
import com.vlms.stock.exception.ProductAlreadyExistsException;
import com.vlms.stock.exception.ProductNotFoundException;
import com.vlms.stock.exception.ProductServiceLogicException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceExceptionHandler {
    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> UserNotFoundExceptionHandler(ProductNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponseDTO<?>> UserAlreadyExistsExceptionHandler(ProductAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = ProductServiceLogicException.class)
    public ResponseEntity<ApiResponseDTO<?>> UserServiceLogicExceptionHandler(ProductServiceLogicException exception) {
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), exception.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDTO<?>> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(new ApiResponseDTO<>(ApiResponseStatus.FAIL.name(), errorMessage.toString()));
    }
}
