package com.example.ProductCategoryService.Services;

import com.example.ProductCategoryService.Client.Fakestore.Client.FakeStoreApiClient;
import com.example.ProductCategoryService.Client.Fakestore.DTOs.FakeStoreProductDTO;
import com.example.ProductCategoryService.Models.Category;
import com.example.ProductCategoryService.Models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class FakeStoreProductServices implements IProductServices {

    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreApiClient fakeStoreApiClient;
    private RedisTemplate<String,Object>redisTemplate;

    public FakeStoreProductServices(RestTemplateBuilder restTemplateBuilder,FakeStoreApiClient fakeStoreApiClient,RedisTemplate<String,Object>redisTemplate) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient=fakeStoreApiClient;
        this.redisTemplate=redisTemplate;
    }

    @Override
    public Product getProductDetails(Long userId, Long productId) {
        return null;
    }

    @Override
    public List<Product> getProducts() {//get all products
        RestTemplate restTemplate=restTemplateBuilder.build();
        FakeStoreProductDTO[] fakeStoreProductDTOS=restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDTO[].class).getBody();
        //conversion logic of ProductDto to Product
        List<Product>products=new ArrayList<>();
        for(FakeStoreProductDTO prdto:fakeStoreProductDTOS){
            products.add(getProduct(prdto));
        }
        return products;
    }

    @Override
    public void putProduct(Long id, Product product) {
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Product>requestEntity=new HttpEntity<>(product,httpHeaders);
        restTemplate.exchange("https://fakestoreapi.com/products/{id}",HttpMethod.PUT,requestEntity,Product.class,id);

        /* RestTemplate restTemplate=restTemplateBuilder.build();
        restTemplate.put("https://fakestoreapi.com/products/{id}",Product.class,id);
        */
    }


    @Override
    public Product getProduct(long productID) {//get the particular/Specific details of the product with id
       // RestTemplate restTemplate=restTemplateBuilder.build();
       // FakeStoreProductDTO fakeStoreProductDTO=restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDTO.class,productID).getBody();


        //check if the product is in Cache
        // if yes
            //Read the product from cache
        //else
            //call FakeStore and Get Result
            //Store result in Cache


        FakeStoreProductDTO fakeStoreProductDTO=null;
        fakeStoreProductDTO=(FakeStoreProductDTO) redisTemplate.opsForHash().get("PRODUCTS",productID);
        if(fakeStoreProductDTO!=null){
            System.out.println("Read from Redis Cache");
            return getProduct(fakeStoreProductDTO);
        }
        fakeStoreProductDTO=fakeStoreApiClient.getProduct(productID);
        System.out.println("Read From FakeStoreAPI");
        redisTemplate.opsForHash().put("PRODUCTS",productID,fakeStoreProductDTO);
        return getProduct(fakeStoreProductDTO);
    }

    @Override
    public String deleteProduct(Long id) {
            return null;
    }


    @Override
    public Product createProduct(Product product) {//create / add the Product
      //  RestTemplate restTemplate=restTemplateBuilder.build();
      //  FakeStoreProductDTO fakeStoreProductDTO=restTemplate.postForEntity("https://fakestoreapi.com/products",product,FakeStoreProductDTO.class).getBody();
        FakeStoreProductDTO fakeStoreProductDTO=getFakeStoreProductDTOFromProduct(product);
        return getProduct(fakeStoreApiClient.createProduct(fakeStoreProductDTO));
    }
    //Own Implementation of RestAPI
    public <T> ResponseEntity<T> patchForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    @Override
    public Product updateProduct(Long id, Product product) {//update a Product
        RestTemplate restTemplate=restTemplateBuilder.build();
      //FakeStoreProductDTO fakeStoreProductDTO=restTemplate.patchForObject("https://fakestoreapi.com/products/{id}", product , FakeStoreProductDTO.class,id);
       FakeStoreProductDTO fakeStoreProductDTO2=getFakeStoreProductDTOFromProduct(product);
       ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTO=patchForEntity(HttpMethod.PATCH, "https://fakestoreapi.com/products/{id}" ,fakeStoreProductDTO2,FakeStoreProductDTO.class,id);

        Product resultantProduct=getProduct(fakeStoreProductDTO.getBody());
        return resultantProduct;
    }


    //Alternative of PatchForEntity if some exception comes up in Patch Method
    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request,
                                                   Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.requestFactory(
                HttpComponentsClientHttpRequestFactory.class
        ).build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private Product getProduct(FakeStoreProductDTO productDTO){
        Product product=new Product();
        product.setId(productDTO.getId());
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        Category category=new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    private FakeStoreProductDTO getFakeStoreProductDTOFromProduct(Product product){
        FakeStoreProductDTO fakeStoreProductDTO=new FakeStoreProductDTO();
        fakeStoreProductDTO.setId(product.getId());
        fakeStoreProductDTO.setTitle(product.getTitle());
        fakeStoreProductDTO.setPrice(product.getPrice());
        fakeStoreProductDTO.setImage(product.getImage());
        fakeStoreProductDTO.setDescription(product.getDescription());
        fakeStoreProductDTO.setCategory(product.getCategory().getName());
        return fakeStoreProductDTO;
    }
}
//FakeStoreProductDto -> product conversion will happen in Service layer
//postForEntity -> forEntity returns ResponseEntity
//postForEntity.getBody()-> returns the object only
//get/patchForObject -> returns the Object only