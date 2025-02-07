package com.vlms.stock.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class ApiResponseDTO<T> {
    private String status;
    private T response;

    public ApiResponseDTO( String status,T name) {
        this.status = status;
        this.response = name;
    }

    public T getResponse() {
        return response;
    }
}
