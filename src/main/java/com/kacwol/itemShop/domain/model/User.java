package com.kacwol.itemShop.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    private String username;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    public User(String login, String password, String username, String email, UserType userType) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.email = email;
        this.userType = userType;
    }
}
