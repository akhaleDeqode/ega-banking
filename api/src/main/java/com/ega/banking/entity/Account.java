package com.ega.banking.entity;

import com.ega.banking.constants.ApplicationConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {

    @Id
    private long accountId;
    private long bankId;
    @Column(columnDefinition = "integer default " + ApplicationConstants.DEFAULT_BALANCE)
    private int balance;

    @OneToMany()
    @JoinColumn(name = "account_id")
    private List<Transaction> transactions;
}
