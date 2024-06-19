package com.example.ProductCategoryService.TableInheritanceExamples.JoinedClass;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "c_jc_instructor")
@PrimaryKeyJoinColumn(name = "user_id")
public class Instructor extends User {
    private String Company;
}

