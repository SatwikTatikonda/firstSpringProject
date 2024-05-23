package com.scaler.demospringboot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Category extends Basemodel{

    private long id;
    private String title;
//    @OneToMany(mappedBy="category")
//    private List<Product> products;

}
