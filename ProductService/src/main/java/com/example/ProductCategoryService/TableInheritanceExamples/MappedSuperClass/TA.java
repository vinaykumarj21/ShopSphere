package com.example.ProductCategoryService.TableInheritanceExamples.MappedSuperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="c_msc_ta")
public class TA extends User {
    private int rating;
}
