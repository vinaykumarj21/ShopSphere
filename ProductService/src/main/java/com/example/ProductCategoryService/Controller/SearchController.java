package com.example.ProductCategoryService.Controller;

import com.example.ProductCategoryService.Client.Fakestore.DTOs.SearchRequestDTO;
import com.example.ProductCategoryService.DTOs.ProductDTO;
import com.example.ProductCategoryService.Models.Product;
import com.example.ProductCategoryService.Services.SearchProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/search")
@RestController
public class SearchController {

    private SearchProductService searchProductService;

    public SearchController(SearchProductService searchProductService){
        this.searchProductService = searchProductService;
    }

    @PostMapping("/product")
    public Page<Product>searchProduct(@RequestBody SearchRequestDTO searchRequestDTO){
//        List<Product> products=searchProductService.searchProduct(searchRequestDTO.getQuery(),
//                searchRequestDTO.getPageSize(),searchRequestDTO.getPageNumber());
//        List<ProductDTO>productDTOS=new ArrayList<>();
//        for(Product product:products){
//            productDTOS.add(getProductDTOFromProduct(product));
//        }
       return searchProductService.searchProduct(searchRequestDTO.getQuery(),searchRequestDTO.getPageNumber(),searchRequestDTO.getPageSize(),searchRequestDTO.getSortParamList());
        //return new ResponseEntity<>(product, HttpStatus.OK);
    }

     ProductDTO getProductDTOFromProduct(Product product){
        ProductDTO productDTO=new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setPrice(product.getPrice());
        productDTO.setTitle(product.getTitle());
        productDTO.setImage(product.getImage());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setImage(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        return productDTO;
    }

}
