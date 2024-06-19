package com.example.ProductCategoryService.DTOs;

import com.example.ProductCategoryService.Models.BaseModel;
import com.example.ProductCategoryService.Models.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO extends BaseModel {
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;//category of a product
    private RatingDTO ratingDTO;
}

/*
    "title": "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        "price": 109.95,
        "description": "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
        "category": "men's clothing",
        "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
        "rating": {
            "rate": 3.9,
            "count": 120
     */