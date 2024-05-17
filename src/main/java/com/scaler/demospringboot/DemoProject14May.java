package com.scaler.demospringboot;

import com.scaler.demospringboot.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProject14May {

    public static void main(String[] args) {
        Product p=new Product();
        SpringApplication.run(DemoProject14May.class, args);

    }

}
