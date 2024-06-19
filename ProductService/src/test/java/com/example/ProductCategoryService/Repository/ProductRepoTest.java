package com.example.ProductCategoryService.Repository;

import com.example.ProductCategoryService.Models.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration
class ProductRepoTest {
    @Autowired
    private CategoryRepo categoryRepo;

  //  @Test
    void demonStrateLoading(){
        Category category= categoryRepo.findById(1L).get();
        System.out.println(category.getName());
    }
}