package com.ega.banking.repository;

import com.ega.banking.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.accountId = :accountId and LOWER(transaction.transactionType) = LOWER(:transactionType)")
    List<Transaction> findAllByType(Long accountId, String transactionType, Pageable pageable);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.accountId = :accountId")
    List<Transaction> findByAccountId(long accountId);

    @Query("SELECT transaction FROM Transaction transaction WHERE transaction.accountId = :accountId")
    List<Transaction> findAllTransactions(Long accountId, Pageable pageable);

    @Query("SELECT COUNT(*) AS transaction_count FROM Transaction WHERE accountId = :accountId")
    int getNumberOfTransactions(long accountId);

    @Query("SELECT COUNT(*) AS transaction_count FROM Transaction transaction WHERE transaction.accountId = :accountId and LOWER(transaction.transactionType) = LOWER(:transactionType)")
    int getNumberOfTransactionsByType(long accountId, String transactionType);
}
