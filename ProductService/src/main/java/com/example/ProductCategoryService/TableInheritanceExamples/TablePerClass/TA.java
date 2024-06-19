package com.example.ProductCategoryService.TableInheritanceExamples.TablePerClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="c_tpc_ta")
public class TA extends User{
    private int rating;
}
