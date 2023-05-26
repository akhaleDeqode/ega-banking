package com.ega.banking.repository;

import com.ega.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT account.id FROM Account account")
    public List<Long> getAllAccountId();

    @Query("SELECT account.balance FROM Account account where account.accountId = :accountNumber")
    public int getBalanceByAccountNumber(Long accountNumber);

    @Modifying
    @Query("UPDATE Account account SET account.balance = :updatedBalance WHERE account.accountId = :accountId")
    void updateBalanceByAccountNumber(int updatedBalance, Long accountId);
}
