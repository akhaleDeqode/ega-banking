package com.ega.banking.controller;

import com.ega.banking.config.JwtService;
import com.ega.banking.dto.BearerTokenDTO;
import com.ega.banking.entity.Account;
import com.ega.banking.entity.User;
import com.ega.banking.error.InvalidAccountIdException;
import com.ega.banking.repository.AccountRepository;
import com.ega.banking.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    private JwtService jwtService = new JwtService();

    @GetMapping("/get")
    public Account getAccountById(@Valid @RequestHeader BearerTokenDTO bearerTokenDTO) {
        String email = jwtService.extractEmailFromJwt(bearerTokenDTO.getToken());
        User user = userService.getUserByEmail(email);
        return accountRepository.findById(user.getAccountId()).orElseThrow(() -> new InvalidAccountIdException());
    }
}
