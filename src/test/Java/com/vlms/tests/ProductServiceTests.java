package com.vlms.tests;

import com.vlms.stock.VLMSStockApiApplication;
import com.vlms.stock.dto.ApiResponseDTO;
import com.vlms.stock.entities.Product;
import com.vlms.stock.exception.ProductServiceLogicException;
import com.vlms.stock.repository.ProductRepository;
import com.vlms.stock.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = VLMSStockApiApplication.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class ProductServiceTests {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetProductById() throws ProductServiceLogicException {
        long productId = 1;
        Product mockProduct = new Product("Prestige Popular Alu 2 lt Pressure Cooker","Prestige","Popular","Aluminium",
                "Pressure Cooker",2,"lt",55);
        mockProduct.setId(1L);
        Mockito.when(productRepository.findById(productId)).thenReturn(mockProduct);

        ResponseEntity<ApiResponseDTO<?>> response = productService.getProductByID(productId);
        EntityModel<Product> productModel = (EntityModel<Product>) response.getBody().getResponse();
        Product product = productModel.getContent();

        assertNotNull(product);
        assertEquals(productId,product.getId());
        assertEquals("Prestige Popular Alu 2 lt Pressure Cooker",product.getProductName());
        assertEquals("Prestige",product.getBrandName());
    }

//    @Test
    public void testGetProductsById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/get/all/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

//    @Test
//    public void testGetAllProducts(){
//        Product product = productRepository.updateProductById(1L,"updated Comments from Tests");
//        assertNotNull(product);
//    }




}
