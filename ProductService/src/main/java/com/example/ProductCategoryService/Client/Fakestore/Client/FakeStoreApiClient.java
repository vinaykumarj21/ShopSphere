package com.example.ProductCategoryService.Client.Fakestore.Client;

import com.example.ProductCategoryService.Client.Fakestore.DTOs.FakeStoreProductDTO;
import com.example.ProductCategoryService.DTOs.ProductDTO;
import com.example.ProductCategoryService.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreApiClient {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDTO getProduct(long productID) {//get the particular/Specific details of the product with id
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,productID).getBody();
        //conversion logic of ProductDto to Product
        return fakeStoreProductDTO;
    }

   /* public void deleteProduct(Long productId){
        RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.delete("https://fakestoreapi.com/products/{id}");
    }*/

    public FakeStoreProductDTO createProduct(FakeStoreProductDTO fakeStoreProductDTO) {//create / add the Product
        RestTemplate restTemplate=restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=restTemplate.postForEntity("https://fakestoreapi.com/products",fakeStoreProductDTO,FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    /*public FakeStoreProductDTO updateProduct(FakeStoreProductDTO fakeStoreProductDTO){
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO fakeStoreProductDTO1=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}",fakeStoreProductDTO,FakeStoreProductDTO.class);
        return fakeStoreProductDTO1;
    }*/

}
