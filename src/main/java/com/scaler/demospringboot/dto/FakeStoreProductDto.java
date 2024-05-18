package com.scaler.demospringboot.dto;

import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {

    private long id;
    private String title;
    private double price;
    public String category;
    private String description;
    private String image;

    public Product toProduct(){

        Product p=new Product();
        p.setId(id);
        p.setTitle(title);
        p.setPrice(price);
        p.setImageUrl(image);
        p.setDescription(description);
        Category cat=new Category();
        cat.setTitle(category);
        return p;
    }


}




