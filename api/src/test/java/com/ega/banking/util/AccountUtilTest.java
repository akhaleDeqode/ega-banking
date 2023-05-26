package com.ega.banking.util;

import com.ega.banking.config.JwtService;
import com.ega.banking.entity.Account;
import com.ega.banking.repository.UserRepository;
import com.ega.banking.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AccountUtilTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountUtil accountUtil;

    private final JwtService jwtService = new JwtService();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateAccountNumber() {
        List<Long> existingNumbers = new ArrayList<>();
        existingNumbers.add(123456789L);

        // Ensure the generated number is not in the existing numbers list
        long accountNumber = accountUtil.generateAccountNumber(existingNumbers);
        assertFalse(existingNumbers.contains(accountNumber));
    }

    @Test
    void testAddAccount() {
        long bankId = 1L;
        long accountNumber = 123456789L;

        Account account = Account.builder()
                .accountId(accountNumber)
                .bankId(bankId)
                .build();

        accountUtil.addAccount(bankId, accountNumber);

        // Verify that the accountService's addAccountOnUserRegistration method was called with the correct account
        verify(accountService, times(1)).addAccountOnUserRegistration(account);
    }
}
