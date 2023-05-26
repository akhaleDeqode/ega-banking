package com.ega.banking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Column(name = "account_id")
    private Long accountId;


    @Column(nullable = false)
    @Min(value = 1, message = "Amount must be greater than or equal to 1")
    @Max(value = 1000000, message = "Amount must be less than or equal to 10000")
    private int amount;
    @Column(nullable = false)

    private LocalDate transactionDate;
    {
        transactionDate = LocalDate.now();
    }
    private String transactionType;

}
