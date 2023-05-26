package com.ega.banking.service;

import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.dto.GetTransactionsResponse;
import com.ega.banking.entity.Account;
import com.ega.banking.entity.Transaction;
import com.ega.banking.dto.TransactionType;
import com.ega.banking.error.InsufficientBalanceException;
import com.ega.banking.error.InvalidTransactionTypeException;
import com.ega.banking.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Transactional
    public ResponseEntity<?> handleTransaction(Transaction transaction) throws InsufficientBalanceException{

        Account account = accountService.getAccountById(transaction.getAccountId());
        List<Transaction> pastTransactions = account.getTransactions();

        int currentBalance = account.getBalance();
        int updatedBalance;
        if (transaction.getTransactionType().equalsIgnoreCase(TransactionType.DEPOSIT.name())) {
            updatedBalance = currentBalance + transaction.getAmount();
        } else if(transaction.getTransactionType().equalsIgnoreCase(TransactionType.WITHDRAW.name())) {
            if (currentBalance < transaction.getAmount())
                throw new InsufficientBalanceException();
            updatedBalance = currentBalance - transaction.getAmount();
        } else {
            throw new InvalidTransactionTypeException();
        }

        pastTransactions.add(transaction);
        account.setBalance(updatedBalance);

        account.setTransactions(pastTransactions);
        accountService.updateBalanceAndTransactionsInAccount(account);

        transactionRepository.save(transaction);

        return ResponseEntity.status(HttpStatusCodes.OK).body(transaction);
    }

    public GetTransactionsResponse getTransactionByType(Long accountId, int pageNumber, int pageSize, String transactionType) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("transactionId").descending());
        List<Transaction> transactions = transactionRepository.findAllByType(accountId, transactionType, pageable);

        return GetTransactionsResponse.builder()
                .transactionList(transactions)
                .listSize(getNumberOfTransactionsByType(accountId, transactionType))
                .build();
    }

    public GetTransactionsResponse getAllTransactions(long accountId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("transactionId").descending());
        List<Transaction> transactions = transactionRepository.findAllTransactions(accountId, pageable);
        int numberOfTransactions = getNumberOfTransactions(accountId);

        return GetTransactionsResponse.builder()
                .transactionList(transactions)
                .listSize(numberOfTransactions)
                .build();
    }

    private int getNumberOfTransactions(long accountId) {
       return transactionRepository.getNumberOfTransactions(accountId);
    }

    private int getNumberOfTransactionsByType(long accountId, String transactionType) {
        return transactionRepository.getNumberOfTransactionsByType(accountId, transactionType);
    }

}
