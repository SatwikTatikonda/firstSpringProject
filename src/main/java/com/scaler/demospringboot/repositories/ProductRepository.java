package com.scaler.demospringboot.repositories;

import com.scaler.demospringboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

//        similar to insert into Product
        Product save(Product product);

//        simlar to select * form Product where title=""
        Product fndByIdTitle(String title);

}
