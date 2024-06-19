package com.example.ProductCategoryService.TableInheritanceExamples.MappedSuperClass;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "c_msc_mentor")
public class Mentor extends User {
    private int no_of_hours;
}
