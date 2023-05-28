package com.ega.banking.service;

import com.ega.banking.config.JwtService;
import com.ega.banking.dto.LoginRequest;
import com.ega.banking.error.WrongPasswordException;
import com.ega.banking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    public String loginUser(LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
        } catch (Exception e) {
            throw new WrongPasswordException();
        }

        var user = userRepository.findByEmail(loginRequest.getEmail());
        String token = jwtService.generateToken(user);
        return token;
    }
}
