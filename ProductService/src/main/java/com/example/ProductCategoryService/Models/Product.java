package com.example.ProductCategoryService.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    private double price;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Category category;//category of a product

}
/*
SearchProductService -> Page<Product>

@JsonManagedReference is an annotation provided by the Jackson library in Java,
which is often used in the context of bidirectional relationships in JPA entities (Java Persistence API)
or any other data model where you have parent-child relationships.

Consider the scenario where you have two entities, let's say Parent and Child,
where Parent has a collection of Child objects. When serializing these entities to JSON,
you might face issues with infinite recursion or stack overflow errors due to circular references
between the parent and child objects.

To avoid this problem, you can use @JsonManagedReference and @JsonBackReference annotations
to define the parent-child relationship and tell Jackson how to handle it during serialization.
 */