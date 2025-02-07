package com.vlms.stock.service;

import com.vlms.stock.exception.ProductAlreadyExistsException;
import com.vlms.stock.exception.ProductNotFoundException;
import com.vlms.stock.exception.ProductServiceLogicException;
import com.vlms.stock.dto.ApiResponseDTO;
import com.vlms.stock.dto.ProductDetailsRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    ResponseEntity<ApiResponseDTO<?>> registerProduct(ProductDetailsRequestDTO newProductDetails)
            throws ProductAlreadyExistsException, ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> getAllProducts()
            throws ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> getProductByID(Long id)
            throws ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> getProductsByProductName(String productName)
            throws ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> getProductsByBrandName(String brandName)
            throws ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> updateProduct(ProductDetailsRequestDTO productDetailsRequestDTO, int id)
            throws ProductNotFoundException, ProductServiceLogicException;

    ResponseEntity<ApiResponseDTO<?>> deleteProduct(int id)
            throws ProductServiceLogicException, ProductNotFoundException;
}
