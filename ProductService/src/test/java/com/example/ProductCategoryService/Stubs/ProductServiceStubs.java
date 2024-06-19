package com.example.ProductCategoryService.Stubs;

import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Services.IProductServices;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ProductServiceStubs will act as a ProductService
//@Service
public class ProductServiceStubs implements IProductServices {

    Map<Long,Product> hm;//in place of DB
    public ProductServiceStubs(){
        hm=new HashMap<Long,Product>();
    }

    @Override
    public Product getProductDetails(Long userId, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        List<Product>products=new ArrayList<>();
        for(long m: hm.keySet()){
            products.add(hm.get(m));
        }
        return products;
    }

    @Override
    public Product getProduct(long id) {
        return hm.get(id);
    }

    @Override
    public Product createProduct(Product product) {
        hm.put(product.getId(),product);
        return hm.get(product.getId());
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        hm.put(id,product);
        return hm.get(id);
    }

    @Override
    public String deleteProduct(Long id) {
        Product product= hm.remove(id);
         return "id:"+id +"Product:"+product;
    }

    @Override
    public void putProduct(Long id, Product product) {

    }
}
