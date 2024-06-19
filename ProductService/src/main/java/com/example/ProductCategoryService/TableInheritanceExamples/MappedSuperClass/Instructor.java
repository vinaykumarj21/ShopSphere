package com.example.ProductCategoryService.TableInheritanceExamples.MappedSuperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_msc_instructor")
public class Instructor extends User {
    private String Company;
}

