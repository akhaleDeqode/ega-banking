package com.ega.banking.controller;

import com.ega.banking.config.JwtService;
import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.dto.BearerTokenDTO;
import com.ega.banking.entity.User;
import com.ega.banking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private JwtService jwtService = new JwtService();

    @GetMapping("/get")
    public ResponseEntity<?> getUserById(@Valid @RequestHeader BearerTokenDTO bearerTokenDTO) {
        String email = jwtService.extractEmailFromJwt(bearerTokenDTO.getToken());
        User user = userService.getUserByEmail(email);

        return ResponseEntity.status(HttpStatusCodes.OK).body(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatusCodes.OK).build();
    }
}
