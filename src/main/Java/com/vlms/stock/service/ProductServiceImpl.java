package com.vlms.stock.service;

import com.vlms.stock.controller.ProductController;
import com.vlms.stock.entities.Product;
import com.vlms.stock.exception.ProductAlreadyExistsException;
import com.vlms.stock.exception.ProductNotFoundException;
import com.vlms.stock.exception.ProductServiceLogicException;
import com.vlms.stock.dto.ApiResponseDTO;
import com.vlms.stock.dto.ApiResponseStatus;
import com.vlms.stock.dto.ProductDetailsRequestDTO;
import com.vlms.stock.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;


    @Override
    public ResponseEntity<ApiResponseDTO<?>> registerProduct(ProductDetailsRequestDTO newProductDetails) throws ProductAlreadyExistsException, ProductServiceLogicException {
        try {
            if (productRepository.findById(newProductDetails.getId()) != null){
                throw new ProductAlreadyExistsException("Registration failed: Product already exists with id " + newProductDetails.getId());
            }
            if (productRepository.findByProductName(newProductDetails.getProductName()) != null){
                throw new ProductAlreadyExistsException("Registration failed: Product already exists with productName " + newProductDetails.getProductName());
            }
            System.out.println("start registerProduct "+newProductDetails);
            Product newProduct = new Product(
                    newProductDetails.getProductName(),newProductDetails.getBrandName(), newProductDetails.getModelName(),newProductDetails.getMaterial(),newProductDetails.getCategory(), newProductDetails.getSize(),newProductDetails.getMeasuringUnit(),newProductDetails.getAvailableQuantity()
            );
            System.out.println("mid registerProduct ");
            // save() is an in built method given by JpaRepository
            productRepository.save(newProduct);
            System.out.println("end registerProduct ");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "New Product has been successfully created!"));

        }catch (ProductAlreadyExistsException e) {
            throw new ProductAlreadyExistsException(e.getMessage());
        }catch (Exception e) {
            //log.error("Failed to create new product : " + e.getMessage());
            System.out.println("registerProduct exception "+e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getAllProducts() throws ProductServiceLogicException {
        try {
            List<Product> products = productRepository.findAll();
            products.forEach(i->System.out.println(i.toString()));
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), products)
                    );

        }catch (Exception e) {
            //log.error("Failed to fetch all users: " + e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getProductByID(Long id) throws ProductServiceLogicException {
        try {
            Product product = productRepository.findById(id);
            System.out.println("getProductByID "+product.toString());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), toHateoasEntityModel(product))
                    );

        }catch (Exception e) {
            //log.error("Failed to fetch all users: " + e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> getProductsByProductName(String productName) throws ProductServiceLogicException {
        try {
            List<Product> products = productRepository.findAllByProductNameContains(productName);
            List<String> productNames = new ArrayList<>();
            products.forEach(pd->productNames.add(pd.getProductName()));
            System.out.println("getProductByProductName "+productName);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), productNames)
                    );

        }catch (Exception e) {
            //log.error("Failed to fetch all users: " + e.getMessage());
            throw new ProductServiceLogicException();
        }
    }
    @Override
    public ResponseEntity<ApiResponseDTO<?>> getProductsByBrandName(String brandName) throws ProductServiceLogicException {
        try {
            List<Product> products = productRepository.findAllByBrandName(brandName);
            System.out.println("getProductByID "+products.toString());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), products)
                    );

        }catch (Exception e) {
            //log.error("Failed to fetch all users: " + e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    public ResponseEntity<ApiResponseDTO<?>> getProductsByCategory(String category) throws ProductServiceLogicException {
        return null;
    }

    public ResponseEntity<ApiResponseDTO<?>> getProductsByModelName(String modelName) throws ProductServiceLogicException {
        return null;
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> updateProduct(ProductDetailsRequestDTO newProductDetailsRequestDTO, int id) throws ProductNotFoundException, ProductServiceLogicException {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
            System.out.println("updateProduct "+product+" request "+newProductDetailsRequestDTO);
            product.setAvailableQuantity(newProductDetailsRequestDTO.getAvailableQuantity());
            product.setModelName(newProductDetailsRequestDTO.getModelName());
            product.setBrandName(newProductDetailsRequestDTO.getBrandName());
            System.out.println("mid updateProduct");
            productRepository.save(product);
            System.out.println("end updateProduct");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Product updated successfully!")
                    );

        }catch(ProductNotFoundException e){
            throw new ProductNotFoundException(e.getMessage());
        }catch(Exception e) {
            //log.error("Failed to update product: " + e.getMessage());
            System.out.println("updateProduct Exception "+e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    @Override
    public ResponseEntity<ApiResponseDTO<?>> deleteProduct(int id) throws ProductServiceLogicException, ProductNotFoundException {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));

            productRepository.delete(product);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ApiResponseDTO<>(ApiResponseStatus.SUCCESS.name(), "Product deleted successfully!")
                    );
        } catch (ProductNotFoundException e) {
            throw new ProductNotFoundException(e.getMessage());
        } catch (Exception e) {
            //log.error("Failed to delete product: " + e.getMessage());
            throw new ProductServiceLogicException();
        }
    }

    private EntityModel<Product> toHateoasEntityModel(Product product) throws ProductServiceLogicException {
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProductByID(product.getId())).withSelfRel();
        Link allProductsLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getAllProducts()).withRel("all-products");

        return EntityModel.of(product,selfLink,allProductsLink);
    }
}
