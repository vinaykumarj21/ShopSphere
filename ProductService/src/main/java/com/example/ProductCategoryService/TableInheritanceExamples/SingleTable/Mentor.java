package com.example.ProductCategoryService.TableInheritanceExamples.SingleTable;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

//"2"-> syntax (Not accepts the values without double quotes")
@Getter
@Setter
@Entity(name = "c_st_mentor")
@DiscriminatorValue(value = "2")
public class Mentor extends User {
    private int no_of_hours;
}
