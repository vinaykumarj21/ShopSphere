package com.example.ProductCategoryService.TableInheritanceExamples.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_st_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String Company;
}

