package com.example.ProductCategoryService.TableInheritanceExamples.JoinedClass;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="c_jc_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class TA extends User {
    private int rating;
}
