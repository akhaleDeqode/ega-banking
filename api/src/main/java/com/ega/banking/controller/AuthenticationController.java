package com.ega.banking.controller;

import com.ega.banking.config.JwtService;
import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.entity.User;
import com.ega.banking.dto.LoginRequest;
import com.ega.banking.dto.LoginResponse;
import com.ega.banking.error.WrongPasswordException;
import com.ega.banking.service.AuthenticationService;
import com.ega.banking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService = new JwtService();

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        userService.checkIfUserExist(loginRequest.getEmail());

        String token = authenticationService.loginUser(loginRequest);
        String email = jwtService.extractEmailFromJwt(token);

        User user = userService.getUserByEmail(email);

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new WrongPasswordException();
        }

        LoginResponse loginResponse = LoginResponse.builder().bearer(token).user(user).build();
        return ResponseEntity.status(HttpStatusCodes.OK).body(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.status(HttpStatusCodes.OK).build();
    }
}
