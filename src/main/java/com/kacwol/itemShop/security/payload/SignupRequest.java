package com.kacwol.itemShop.security.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    private String username;

    private String login;

    private String email;

    private String password;

}
