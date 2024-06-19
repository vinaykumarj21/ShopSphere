package com.example.ProductCategoryService.TableInheritanceExamples.TablePerClass;


import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_tpc_instructor")
public class Instructor extends User{
    private String Company;
}

