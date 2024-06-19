package com.example.ProductCategoryService.TableInheritanceExamples.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="c_st_ta")
@DiscriminatorValue(value = "1")
public class TA extends User {
    private int rating;
}
