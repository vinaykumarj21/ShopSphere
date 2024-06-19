package com.example.ProductCategoryService.Models;

import lombok.Getter;
import lombok.Setter;


import jakarta.persistence.*;


import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date CreatedAt;
    private Date LastUpdatedAt;
    private Status status;
}
