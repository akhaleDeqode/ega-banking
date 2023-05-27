package com.ega.banking.service;

import com.ega.banking.entity.User;
import com.ega.banking.error.InvalidUserIdException;
import com.ega.banking.error.UserNotExistException;
import com.ega.banking.repository.AccountRepository;
import com.ega.banking.repository.UserRepository;
import com.ega.banking.util.AccountUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountUtil accountUtil;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registerUser(User user) {
        List<Long> allAccountNumber= accountRepository.getAllAccountId();
        Long accountNumber = accountUtil.generateAccountNumber(allAccountNumber);
        accountUtil.addAccount(accountNumber);
        user.setAccountId(accountNumber);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new InvalidUserIdException());
    }

    public User getUserByEmail(String email) {
        return (User) userRepository.findByEmail(email);
    }

    public User getUserByAccountId(long accountId) {
        return userRepository.findUserByAccountId(accountId);
    }

    public void checkIfUserExist(String email) {
        boolean checkEmailExist = userRepository.existsByEmail(email);

        if (!checkEmailExist) {
            throw new UserNotExistException();
        }
    }
}
