package com.scaler.demospringboot.service;

import com.scaler.demospringboot.exceptions.ProductNotFound;
import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.model.Product;
import org.springframework.data.domain.Page;

import java.util.*;

public interface ProductService {

    Product getSingleProduct(long productId) throws ProductNotFound;
    Page<Product> getAllProducts(int pageSize, int pageNumber, String fieldName);
    Product createProduct(Product product);
    Product updateProduct(Product product,long id);
    String removeProduct(long productId);
    List<Category> getAllCategories();
    List<Product> getProductsByCategory(String categoryName);
    String removeCategory(long categoryId);


}
