package com.example.ProductCategoryService.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.*;



@MappedSuperclass
public abstract class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;
        private String name;
        private String email;
}
//Abstract class is must
//No need to specify the Inheritance Type
//add MappedSuperClass Annotation
//no need to mention @Entity for Abstract Class