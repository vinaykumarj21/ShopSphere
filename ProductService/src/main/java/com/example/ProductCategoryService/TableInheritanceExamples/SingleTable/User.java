package com.example.ProductCategoryService.TableInheritanceExamples.SingleTable;

import jakarta.persistence.*;


@Entity(name = "c_st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type" , discriminatorType = DiscriminatorType.INTEGER)
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String name;
        private String email;
}

//one single table will be created that will be having all the column values in it.(Discriminated by the DiscriminatorType Annotation value)
//table -> c_st_user will be created
//Need to mention respective @Entity(name = "c_st_ta") Annotation over the classes