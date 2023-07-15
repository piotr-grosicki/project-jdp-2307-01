package com.kodilla.ecommercee.domain;

import com.kodilla.ecommercee.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import java.util.ArrayList;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "USER_KEY")
    private String userKey;

    @Column(name = "USER_KEYVALIDITY")
    private LocalDateTime keyValidity;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ACTIVE")
    private UserStatus isActive;

    @OneToMany(
            targetEntity = Cart.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(targetEntity = Order.class,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public User(String firstname, UserStatus isActive) {
        this.firstname = firstname;
        this.isActive = isActive;
    }

}
