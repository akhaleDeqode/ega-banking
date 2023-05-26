package com.ega.banking.controller;

import com.ega.banking.config.JwtService;
import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.entity.User;
import com.ega.banking.dto.LoginRequest;
import com.ega.banking.dto.LoginResponse;
import com.ega.banking.service.AuthenticationService;
import com.ega.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    private final JwtService jwtService = new JwtService();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        userService.checkIfUserExist(loginRequest.getEmail());
        String token = authenticationService.loginUser(loginRequest);
        String email = jwtService.extractEmailFromJwt(token);

        User user = userService.getUserByEmail(email);

        LoginResponse loginResponse = LoginResponse.builder().bearer(token).user(user).build();
        return ResponseEntity.status(HttpStatusCodes.OK).body(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.status(HttpStatusCodes.OK).build();
    }
}
