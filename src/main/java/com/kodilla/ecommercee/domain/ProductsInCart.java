package com.kodilla.ecommercee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProductsInCart {
    @Id
    private Long id;
}
