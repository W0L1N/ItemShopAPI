package com.kacwol.itemShop.security.config;

public class JWTConstants {
    public static final long EXPIRATION_TIME = 3600000;
    public static final long REFRESH_EXPIRATION_TIME = 864_000_000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
