package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Table(name = "CARTS")
public class Cart {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID", unique = true)
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")}
    )
    private List<Product> products = new ArrayList<>();

}
