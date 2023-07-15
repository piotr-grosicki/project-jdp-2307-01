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
    @Column(name = "CART_ID", unique = true)
    private long id;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "JOIN_PRODUCTS_CARTS",
            joinColumns = {@JoinColumn(name = "CART_ID", referencedColumnName = "CART_ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")}
    )
    private List<Product> products = new ArrayList<>();

    public Cart(boolean isActive, User user) {
        this.isActive = isActive;
        this.user = user;
    }

    public Cart(long id, boolean isActive, User user) {
        this.id = id;
        this.isActive = isActive;
        this.user = user;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
