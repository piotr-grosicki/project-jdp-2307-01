package com.kodilla.ecommercee.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "ORDER_ID", unique = true)
    private Long id;

    @Column(name = "COST")
    private BigDecimal cost;

    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "CART_ID")
    private Cart cart;


}
