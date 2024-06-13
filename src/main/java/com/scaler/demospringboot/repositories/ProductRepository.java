package com.scaler.demospringboot.repositories;

import com.scaler.demospringboot.model.Product;
import com.scaler.demospringboot.repositories.projections.ProductProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data .repository.query.Param;
import org.springframework.stereotype.Repository;


//import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//        similar to insert into Product
         Product save(Product product);

        Page<Product> findAll(Pageable pageable);
//         Page of products will return List of Products asn well as other
//    infor mation related to pagination





//        simlar to select * form Product where title=""
//        Product fndByIdTitle(String title);

//        Product findById(long id);
//        how to implement HQl----------

        //    we are using categoryId of line 23 in line 22
//        @Query("select p from Product p where p.category.id= :categoryId")
//        List<Product> getProductsByCategoryId(@Param("categortId")Long categoryId);

//        how to implement native query

//        @Query(value="select p from product p where p.category_id= :categoryId",nativeQuery=true)
//        List<Product> getProductsByCategoryId(@Param("categortId")Long categoryId);


        //HQL with projections----------

//    projections:allows u to fetch specific cols from DB
//    @Query("select p.title as title,p.id as id from Product p where p.category.id= :categoryId")
//    List<ProductProjections> getProductsByCategoryId(@Param("categortId")Long categoryId);




}
