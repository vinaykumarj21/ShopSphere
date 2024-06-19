package com.example.ProductCategoryService.Client.Fakestore.DTOs;

import com.example.ProductCategoryService.Models.SortParam;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchRequestDTO {
    private String query;
    private int pageSize;
    private int pageNumber;

    private List<SortParam> sortParamList=new ArrayList<>();
//Lists of Parameters - for Custom Sorts
}
//pageNumber will start from 0