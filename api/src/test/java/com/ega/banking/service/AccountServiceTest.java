package com.ega.banking.service;

import com.ega.banking.constants.TestConstants;
import com.ega.banking.entity.Account;
import com.ega.banking.error.InvalidAccountIdException;
import com.ega.banking.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addAccountOnUserRegistration_ShouldSaveAccount() {
        Account account = Account.builder()
                .build();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.addAccountOnUserRegistration(account);

        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void getBalanceByAccountNumber_ShouldReturnBalance() {
        Long accountNumber = TestConstants.ACCOUNT_NUMBER;
        int expectedBalance = TestConstants.EXPECTED_BALANCE;
        when(accountRepository.getBalanceByAccountNumber(eq(accountNumber))).thenReturn(expectedBalance);

        int balance = accountService.getBalanceByAccountNumber(accountNumber);

        assertEquals(expectedBalance, balance);
        verify(accountRepository, times(1)).getBalanceByAccountNumber(accountNumber);
    }

    @Test
    void updateBalanceByAccountNumber_ShouldUpdateBalance() {
        int updatedBalance = TestConstants.UPDATED_BALANCE;
        Long accountId = TestConstants.ACCOUNT_ID;

        accountService.updateBalanceByAccountNumber(updatedBalance, accountId);

        verify(accountRepository, times(1)).updateBalanceByAccountNumber(updatedBalance, accountId);
    }

    @Test
    void getAccountById_ValidAccountId_ShouldReturnAccount() {
        Long accountId = TestConstants.ACCOUNT_ID;
        Account account = Account.builder()
                .build();
        when(accountRepository.findById(eq(accountId))).thenReturn(Optional.of(account));

        Account result = accountService.getAccountById(accountId);

        assertNotNull(result);
        assertEquals(account, result);
        verify(accountRepository, times(1)).findById(accountId);
    }

    @Test
    void getAccountById_InvalidAccountId_ShouldThrowInvalidAccountIdException() {
        Long invalidAccountId = TestConstants.INVALID_ACCOUNT_ID;
        when(accountRepository.findById(eq(invalidAccountId))).thenReturn(Optional.empty());

        assertThrows(InvalidAccountIdException.class, () -> accountService.getAccountById(invalidAccountId));
        verify(accountRepository, times(1)).findById(invalidAccountId);
    }

    @Test
    void updateBalanceAndTransactionsInAccount_ShouldSaveAccount() {
        Account account = Account.builder()
                .build();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.updateBalanceAndTransactionsInAccount(account);

        verify(accountRepository, times(1)).save(account);
    }
}
