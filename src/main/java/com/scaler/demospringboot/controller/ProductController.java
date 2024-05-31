package com.scaler.demospringboot.controller;


import com.scaler.demospringboot.dto.ErrorDto;
import com.scaler.demospringboot.exceptions.ProductNotFound;
import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import com.scaler.demospringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Qualifier("selfProductService")
    @Autowired
    private final ProductService productservice;

    public ProductController(@Qualifier("selfProductService") ProductService productservice) {
        this.productservice = productservice;
    }


//    public ProductController( @Qualifier("selfProductService") ProductService productservice) {
//        this.productservice = productservice;
//    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody Product product) {
//        whenever someone is post request on /products
//        the following method will be executed

        Product postRequestResponse=productservice.createProduct(product);

        return postRequestResponse;


    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable long id) throws ProductNotFound {
//        whenever some wish to get one product on /products/id
//          the following method will be executed

        Product currentProduct= productservice.getSingleProduct(id);
        ResponseEntity<Product> res= new ResponseEntity<>(
                currentProduct, HttpStatus.NOT_FOUND
        );


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

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
        ErrorDto errorDto=new ErrorDto();
        errorDto.setMessage(e.getMessage());

        return new ResponseEntity<>(errorDto,HttpStatus.NOT_FOUND);
    }



}
