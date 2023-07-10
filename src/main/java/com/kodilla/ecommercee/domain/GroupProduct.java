package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PRODUCT_GROUPS")
public class GroupProduct {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name="PRODUCT_GROUP_ID", unique=true)
    private long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "groupProduct",
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    public GroupProduct(String name) {
        this.name = name;
    }

}
