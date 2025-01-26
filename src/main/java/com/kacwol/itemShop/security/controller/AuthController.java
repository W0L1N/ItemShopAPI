package com.kacwol.itemShop.security.controller;


import com.kacwol.itemShop.security.model.CustomUserDetails;
import com.kacwol.itemShop.security.payload.AuthResponse;
import com.kacwol.itemShop.security.payload.LoginRequest;
import com.kacwol.itemShop.security.payload.SignupRequest;
import com.kacwol.itemShop.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = ((CustomUserDetails) authentication.getPrincipal());
        String token = userService.login(customUserDetails.getEmail());
//        String refreshToken= userService.createRefreshToken(customUserDetails.getId());
        return ResponseEntity.ok(new AuthResponse(token, null));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> register( @RequestBody SignupRequest signupRequest) {
        if (userService.register(signupRequest)) {
            return new ResponseEntity<>("Success! User has registered", HttpStatus.CREATED);
        }
        return ResponseEntity.ok("User registration field");
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<String> refreshToken(@RequestParam String token) {
        return ResponseEntity.ok(userService.refreshToken(token));
    }
}
