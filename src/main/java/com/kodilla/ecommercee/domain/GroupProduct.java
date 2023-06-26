package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "PRODUCT_GROUPS")
public class GroupProduct {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="ID", unique=true)
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupProduct",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    public GroupProduct() {

    }

    public GroupProduct(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
