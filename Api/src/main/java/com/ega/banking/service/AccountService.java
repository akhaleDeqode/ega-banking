package com.ega.banking.service;

import com.ega.banking.entity.Account;
import com.ega.banking.error.InvalidAccountIdException;
import com.ega.banking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public void addAccountOnUserRegistration(Account account) {
        accountRepository.save(account);
    }

    public int getBalanceByAccountNumber(Long accountNumber) {
        return accountRepository.getBalanceByAccountNumber(accountNumber);
    }

    public void updateBalanceByAccountNumber(int updatedBalance, Long accountId) {
        accountRepository.updateBalanceByAccountNumber(updatedBalance, accountId);
    }

    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new InvalidAccountIdException());
    }

    public void updateBalanceAndTransactionsInAccount(Account account) {
        accountRepository.save(account);
    }
}
