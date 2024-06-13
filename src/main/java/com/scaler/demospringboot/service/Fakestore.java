package com.scaler.demospringboot.service;

import ch.qos.logback.classic.util.LogbackMDCAdapter;
import com.scaler.demospringboot.dto.FakeStoreProductDto;
import com.scaler.demospringboot.exceptions.ProductNotFound;
import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("fakestore")
//spring acknowledges here the connection between controller and service takes place using @Service



public class Fakestore implements ProductService{


    private RestTemplate restTemplate;
    private RedisTemplate redisTemplate;

    public Fakestore(RestTemplate restTemplate, RedisTemplate redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) throws ProductNotFound {

//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDto= restTemplate.getForEntity(
//
//                "https://fakestoreapi.com/products/"+productId,
//                FakeStoreProductDto.class
//
//        );

//        return fakeStoreProductDto.toProduct();

//        implementing redis--------------
        Product productFromRedis = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCTS_"+productId);

        if(productFromRedis!=null){
            return productFromRedis;
        }
        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject(

                "https://fakestoreapi.com/products/"+productId,
                FakeStoreProductDto.class

        );

        if (fakeStoreProductDto==null){
            throw new ProductNotFound(" product not found with id "+productId);
        }

        redisTemplate.opsForHash().put("PRODUCTS","PRODUCTS_"+productId,fakeStoreProductDto.toProduct());
        return fakeStoreProductDto.toProduct();


    }

    @Override
    public Page<Product> getAllProducts(int pageSize, int pageNumber, String fieldName,String order) {

        return null;
//        List<Product>lst=new ArrayList<>();
//        FakeStoreProductDto[] fakeStoreProductDto= restTemplate.getForObject(
//                "https://fakestoreapi.com/products", FakeStoreProductDto[].class
//
//        );
//        List<FakeStoreProductDto> fakeStoreProductDtoLst = new ArrayList<>(Arrays.asList(fakeStoreProductDto));
//
//
//        for(FakeStoreProductDto fs:fakeStoreProductDtoLst){
//           lst.add(fs.toProduct());
//       }
//
//       System.out.println(lst);
//       return lst;
    }

    @Override
    public Product createProduct(Product product) {

        FakeStoreProductDto fs= new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());

        FakeStoreProductDto response =restTemplate.postForObject(
            "https://fakestoreapi.com/products",
               fs,
               FakeStoreProductDto.class
        );

        return response.toProduct();

    }

    @Override

    public Product updateProduct(Product product,long id) {

        FakeStoreProductDto fs= new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
//        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());
        fs.setDescription(product.getDescription());
        fs.setPrice(2000);

        restTemplate.put(

                "https://fakestoreapi.com/products/"+id,
                fs
        );

        return fs.toProduct();

    }


    @Override
    public String removeProduct(long productId) {

        restTemplate.delete("https://fakestoreapi.com/products/"+productId);
        return "product with "+productId+" was removed";
    }


    @Override
    public List<Category> getAllCategories() {


//        return  null;

        String[] fakestorecategories= restTemplate.getForObject(

                "https://fakestoreapi.com/products/categories", String[].class

        );
        List<Category>categoryList = new ArrayList<>();
        List<Product> productList=new ArrayList<>();
        long id=1L;
        for(String cat:fakestorecategories){
            categoryList.add(new Category(cat,productList));
//            id+=1;
        }

        return categoryList;
    }

    public List<Product> getProductsByCategory(String category) {

        List<Product>lstnew=new ArrayList<>();
        FakeStoreProductDto[] fakeStoreProductDto= restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class

        );
        List<FakeStoreProductDto> fakeStoreProductDtoLst = new ArrayList<>(Arrays.asList(fakeStoreProductDto));


        for(FakeStoreProductDto fs:fakeStoreProductDtoLst){
            System.out.println(fs.category);
            if (fs.category.equals(category)){
                lstnew.add(fs.toProduct());
            }
        }


        return lstnew;
    }

    @Override
    public String removeCategory(long categoryId) {
        return null;
//        categoryRepository.deleteById(categoryId);
//        return "Category with "+categoryId+" is deleted successfully ";
    }


}
