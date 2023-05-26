package com.ega.banking.service;

import com.ega.banking.constants.TestConstants;
import com.ega.banking.entity.Account;
import com.ega.banking.entity.Transaction;
import com.ega.banking.error.InsufficientBalanceException;
import com.ega.banking.dto.TransactionType;
import com.ega.banking.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void handleTransaction_DepositTransaction_ShouldReturnTransactionResponseEntity() throws InsufficientBalanceException {
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.DEPOSIT.name())
                .amount(TestConstants.AMOUNT)
                .build();

        List<Transaction> pastTransactions = new ArrayList<>();
        Account account = Account.builder()
                .balance(TestConstants.BALANCE)
                .transactions(pastTransactions)
                .build();
        when(accountService.getAccountById(any())).thenReturn(account);

        ResponseEntity<?> responseEntity = transactionService.handleTransaction(transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
        assertEquals(TestConstants.UPDATED_AMOUNT_DEPOSIT, account.getBalance());
        assertTrue(account.getTransactions().contains(transaction));
        verify(transactionRepository, times(1)).save(transaction);
        verify(accountService, times(1)).updateBalanceAndTransactionsInAccount(account);
    }


    @Test
    void handleTransaction_WithdrawTransactionWithSufficientBalance_ShouldReturnTransactionResponseEntity() throws InsufficientBalanceException {
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.WITHDRAW.name())
                .amount(TestConstants.AMOUNT)
                .build();

        Account account = new Account();
        account.setBalance(TestConstants.BALANCE);
        List<Transaction> pastTransactions = new ArrayList<>();
        account.setTransactions(pastTransactions);
        when(accountService.getAccountById(any())).thenReturn(account);

        ResponseEntity<?> responseEntity = transactionService.handleTransaction(transaction);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
        assertEquals(account.getBalance(), TestConstants.UPDATED_AMOUNT_WITHDRAW);
        assertTrue(account.getTransactions().contains(transaction));
        verify(transactionRepository, times(1)).save(transaction);
        verify(accountService, times(1)).updateBalanceAndTransactionsInAccount(account);
    }

    @Test
    void handleTransaction_WithdrawTransactionWithInsufficientBalance_ShouldThrowInsufficientBalanceException() throws InsufficientBalanceException {
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.WITHDRAW.name())
                .amount(TestConstants.AMOUNT)
                .build();

        Account account = Account.builder()
                .build();
        account.setBalance(TestConstants.INSUFFICIENT_BALANCE);
        when(accountService.getAccountById(any())).thenReturn(account);

        assertThrows(InsufficientBalanceException.class, () -> transactionService.handleTransaction(transaction));

        assertEquals(TestConstants.INSUFFICIENT_BALANCE, account.getBalance());

    }
}