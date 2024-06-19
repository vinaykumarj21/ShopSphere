package com.example.ProductCategoryService.Services;

import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Models.SortParam;
import com.example.ProductCategoryService.Models.SortType;
import com.example.ProductCategoryService.Repository.ProductRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchProductService {
    private ProductRepo productRepo;

    public SearchProductService(ProductRepo productRepo){
        this.productRepo=productRepo;
    }

    public Page<Product> searchProduct(String query, int pageNumber, int pageSize, List<SortParam> sortParamLists){
       // Sort sort=Sort.by("price").descending();

        Sort sort=null;
        if(!sortParamLists.isEmpty()){
            if(sortParamLists.get(0).getSortType().equals(SortType.ASC)){
                sort=Sort.by(sortParamLists.get(0).getParamName());
            }else{
                sort=Sort.by(sortParamLists.get(0).getParamName()).descending();
            }
        }
        for(int i=1;i<sortParamLists.size();i++){
            if(sortParamLists.get(i).getSortType().equals(SortType.ASC)){
                sort=sort.and(Sort.by(sortParamLists.get(0).getParamName()));
            }else{
                sort=sort.and(Sort.by(sortParamLists.get(i).getParamName()).descending());
            }
        }

       return productRepo.findByTitleEquals(query, PageRequest.of(pageNumber,pageSize,sort));
    }

}
