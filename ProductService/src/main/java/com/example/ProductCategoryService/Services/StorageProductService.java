package com.example.ProductCategoryService.Services;

import com.example.ProductCategoryService.DTOs.UserDTO;
import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;


@Service
public class StorageProductService implements IProductServices{
    //this class will interact with ProductRepo

    @Autowired
    private ProductRepo productRepo;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public Product getProductDetails(Long userId,Long productId){
        Product product=productRepo.findById(productId).get();
        System.out.println(product.getTitle());

        RestTemplate restTemplate1=restTemplate();
        UserDTO userDTO=restTemplate1.getForEntity("http://localhost:8082/users/{id}",UserDTO.class,userId).getBody();
        if(userDTO.getEmail()==null)
            System.out.println("Null");

        System.out.println(userDTO.getEmail());
        return product;
    }


    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public Product getProduct(long id) {
        return productRepo.findById(id).get();
    }

    @Override
    public Product createProduct(Product product) {
      return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public String deleteProduct(Long id) {
       Optional<Product>optionalProduct=productRepo.findById(id);
       if(!optionalProduct.isEmpty()){
           productRepo.deleteById(id);
           return "Deleted Successfully";
       }
       return "product not present";
    }

    @Override
    public void putProduct(Long id, Product product) {
        createProduct(product);

    }
}
