package com.vlms.tests;

import com.vlms.stock.VLMSStockApiApplication;
import com.vlms.stock.entities.Product;
import com.vlms.stock.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest(classes = VLMSStockApiApplication.class)
@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;
    @Test
    public void testGetAllProducts(){
        Product product = productRepository.updateProductById(1L,"updated Comments from Tests");
    }
}
