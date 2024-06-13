package com.scaler.demospringboot.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class Basemodel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private  long id;
    private Date CreatedAT;
    private Date updatedAt;
    private boolean isDeleted;

}
