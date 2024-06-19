package com.example.ProductCategoryService.Client.Fakestore.DTOs;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FakeStoreProductDTO implements Serializable {
    private long id;
    private String title;
    private String description;
    private String image;
    private double price;
    private String category;//category of a product
   private FakeStoreRatingDTO ratingDTO;
}
