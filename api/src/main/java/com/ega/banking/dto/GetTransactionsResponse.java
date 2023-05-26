package com.ega.banking.dto;

import com.ega.banking.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetTransactionsResponse {

    private int listSize;
    private List<Transaction> transactionList;
}
