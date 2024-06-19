package com.example.ProductCategoryService.Controller;


import com.example.ProductCategoryService.DTOs.ProductDTO;
import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Stubs.ProductServiceStubs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.assertEquals;
@ContextConfiguration
@SpringBootTest
public class ProductControllerProductFlowTest {

    @Autowired
    ProductController productController;

    @Autowired
    ProductServiceStubs productServiceStubs;//we want productController to use the Implementation from
    // productServiceStub class hence @Autowired is used and not the MockBen
    //In case of MockBean we have to instruct the object -> when xyz called use this/do this


   // @Test
    public void Create_and_Fetch_and_Update_RunsSuccessfully(){

        //Arrange
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(2L);
        productDTO.setTitle("xyz");
        productDTO.setCategory("ABC");

        //Act
        productController.createProduct(productDTO);
        ResponseEntity<Product>productResponseEntity=productController.getProducts(2L);

        productDTO.setTitle("pqr");
        productDTO.setPrice(100);
        productController.updateProduct(2L,productDTO);
        ResponseEntity<Product>updatedResponseEntity=productController.getProducts(2L);


        //Assert
        assertEquals("xyz",productResponseEntity.getBody().getTitle());
        assertEquals("ABC",productResponseEntity.getBody().getCategory().getName());
        assertEquals("pqr",updatedResponseEntity.getBody().getTitle());
        assertEquals(100,productResponseEntity.getBody().getPrice());
    }
}
