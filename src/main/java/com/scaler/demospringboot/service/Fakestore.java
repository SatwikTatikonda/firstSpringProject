package com.scaler.demospringboot.service;

import com.scaler.demospringboot.dto.FakeStoreProductDto;
import com.scaler.demospringboot.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
//spring acknowledges here the connection between controller and service takes place using @Service



public class Fakestore implements ProductService{


    private RestTemplate restTemplate;

    public Fakestore(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getSingleProduct(long productId) {

        FakeStoreProductDto fakeStoreProductDto= restTemplate.getForObject(

                "https://fakestoreapi.com/products/"+productId,
                FakeStoreProductDto.class

        );

        return fakeStoreProductDto.toProduct();


    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product createProduct(Product product) {

        FakeStoreProductDto fs= new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setCatergory(product.getCategory().getTitle());
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
}
