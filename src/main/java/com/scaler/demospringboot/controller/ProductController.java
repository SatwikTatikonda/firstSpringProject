package com.scaler.demospringboot.controller;


import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import com.scaler.demospringboot.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

//    POST
//     Request Body{
//
//                 "title": "test product",
//                "price": 13.5,
//                "description": "lorem ipsum set",
//                "image": "https://i.pravatar.cc",
//                "category": "electronic"
//     }

    private ProductService productservice;


    public ProductController(ProductService productservice) {
        this.productservice = productservice;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
//        whenever someone is post request on /products
//        the following method will be executed

        Product postRequestResponse=productservice.createProduct(product);

        return postRequestResponse;


    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable long id) {

//        whenever some wish to get one product on /products/id
//          the following method will be executed

        Product currentProduct= productservice.getSingleProduct(id);
        return currentProduct;


    }

    @GetMapping("/products")
    public List<Product> getAllproducts(){

        List<Product> allProducts =productservice.getAllProducts();

        return allProducts;

    }

    @PutMapping("/products/{id}")
    public Product putProduct(@RequestBody Product product,@PathVariable long id ){
        return productservice.updateProduct(product,id);

    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable long id) {

        return productservice.removeProduct(id);

    }

    @GetMapping("/products/categories")
    public List<Category> getAllcategories(){

        return productservice.getAllCategories();
    }

    @GetMapping("/products/category/{categoryName}")
    public List<Product> getProductsByCategory(@PathVariable String categoryName) {

        return productservice.getProductsByCategory(categoryName);
    }



}
