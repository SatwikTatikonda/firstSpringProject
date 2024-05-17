package com.scaler.demospringboot.service;

import com.scaler.demospringboot.model.Product;
import java.util.*;

public interface ProductService {

    Product getSingleProduct(long productId);
    List<Product> getAllProducts();
    Product createProduct(Product product);

}
