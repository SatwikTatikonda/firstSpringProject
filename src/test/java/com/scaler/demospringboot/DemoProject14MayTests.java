package com.scaler.demospringboot;

import com.scaler.demospringboot.model.Category;
import com.scaler.demospringboot.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoProject14MayTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

//    @Test
//    void fetchCategoryLazy(){
//        Category cat=categoryRepository.findById()
//    }

}
