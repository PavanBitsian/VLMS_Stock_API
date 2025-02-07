package com.vlms.stock.controller;

import com.vlms.stock.entities.Product;
import com.vlms.stock.exception.ProductAlreadyExistsException;
import com.vlms.stock.exception.ProductNotFoundException;
import com.vlms.stock.exception.ProductServiceLogicException;
import com.vlms.stock.dto.ApiResponseDTO;
import com.vlms.stock.dto.ProductDetailsRequestDTO;
import com.vlms.stock.exception.ProductServiceLogicException;
import com.vlms.stock.service.ProductService;
import com.vlms.stock.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/products")
public class ProductController {
    Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    public ProductService productService;

    @PostMapping("/new")
    public ResponseEntity<ApiResponseDTO<?>> registerProduct(@Valid @RequestBody ProductDetailsRequestDTO productDetailsRequestDTO) throws ProductAlreadyExistsException, ProductServiceLogicException {
        System.out.println("Start registerProduct");
        return productService.registerProduct(productDetailsRequestDTO);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponseDTO<?>> getAllProducts() throws ProductServiceLogicException {
        System.out.println("Start getAllProducts");
        logger.info("Start getAllProducts");
        logger.debug("Start getAllProducts debug");
        logger.trace("Start getAllProducts trace");
        return productService.getAllProducts();
    }

    @Cacheable("products")
    @GetMapping("/get/all/{id}")
    public ResponseEntity<ApiResponseDTO<?>> getProductByID(@PathVariable Long id) throws ProductServiceLogicException {
        System.out.println("Start getProductByID");
        return productService.getProductByID(id);
    }

    @GetMapping("/searchProductName")
    public ResponseEntity<ApiResponseDTO<?>> searchProductName(@RequestParam String productName) throws ProductServiceLogicException {
        System.out.println("Start searchProductName");
        logger.info("Start searchProductName");
        return productService.getProductsByProductName(productName);
    }

    @GetMapping("/get/all/brandname/{brandName}")
    public ResponseEntity<ApiResponseDTO<?>> getProductByBrandName(@PathVariable String brandName) throws ProductServiceLogicException {
        System.out.println("Start getProductByBrandName");
        return productService.getProductsByBrandName(brandName);
    }

    @CachePut("products")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDTO<?>> updateProduct(@Valid @RequestBody ProductDetailsRequestDTO productDetailsRequestDTO, @PathVariable int id)
            throws ProductNotFoundException, ProductServiceLogicException {
        System.out.println("Start updateProduct");
        return productService.updateProduct(productDetailsRequestDTO, id);
    }

    @CacheEvict("products")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDTO<?>> deleteProduct(@PathVariable int id)
            throws ProductNotFoundException, ProductServiceLogicException {
        System.out.println("Start deleteProduct");
        return productService.deleteProduct(id);
    }

}
