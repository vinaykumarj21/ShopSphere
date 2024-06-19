package com.example.ProductCategoryService.Controller;

import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Services.IProductServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration
@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductServices productServices;//external dependency for productcontroller class ( we have to Mock the External Dependency)

    @Autowired
    private ObjectMapper objectMapper;//for conversion of Object to String

  //  @Test
    public void Test_getproducts_ReceiveSuccessful_Response() throws Exception {
        List<Product> productList=new ArrayList<>();
        Product product=new Product();
        product.setTitle("Iphone12");
        Product product1=new Product();
        product1.setTitle("MacBook");
        productList.add(product);
        productList.add(product1);
        when(productServices.getProducts()).thenReturn(productList);


        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        //.andExpect(MockMvcResultMatchers.content().string("[]"));
                        .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(productList)))
                        .andExpect(jsonPath("$.length()").value(2))//No of Products in the list
                        .andExpect(jsonPath("$[0].title").value("Iphone12"));

        //Object->Json->String
    }
    //String json = "{ \"title\" : \"Black\", \"price\" : \"10000\" }";
    //Product product2=objectMapper.readValue(json,Product.class); from string -> object conversion

   // @Test
    public void Test_createProduct_ReceivedSuccessfulResponse() throws Exception {
        Product productToCreate=new Product();
        productToCreate.setTitle("Iphone12");
        productToCreate.setPrice(1000);

        Product productToExpect=new Product();
        productToExpect.setId(1000L);
        productToExpect.setTitle("Iphone12");
        productToExpect.setDescription("new brand Iphone");

        when(productServices.createProduct(any(Product.class))).thenReturn(productToExpect);
        //Instructing to Mock -> when request to createProduct will come Just Return me 'productToExpect' this product

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)//contentType is Imp to mention
                .content(objectMapper.writeValueAsString(productToCreate)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(productToExpect)));

    }

   // @Test
    public void Test_updateProduct_ReceiveSuccessfulResponse(){

    }


}
