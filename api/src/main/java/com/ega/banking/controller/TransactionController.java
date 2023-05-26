package com.ega.banking.controller;

import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.dto.BearerTokenDTO;
import com.ega.banking.dto.GetTransactionsResponse;
import com.ega.banking.entity.Transaction;
import com.ega.banking.dto.TransactionType;
import com.ega.banking.service.TransactionService;
import com.ega.banking.util.AccountUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountUtil accountUtil;



    @PostMapping("/")
    public ResponseEntity<?> depositMoney(@RequestBody Transaction transaction) {
        return transactionService.handleTransaction(transaction);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTransaction(
            @Valid @RequestHeader BearerTokenDTO bearerTokenDTO,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        Long accountId = accountUtil.getAccountIdFromToken(bearerTokenDTO.getToken());

        GetTransactionsResponse transactionsResponse = transactionService.getAllTransactions(accountId, pageNumber, pageSize);
        return ResponseEntity.status(HttpStatusCodes.OK).body(transactionsResponse);
    }

    @GetMapping("/get/deposits")
    public ResponseEntity<?> getAllDepositTransaction(
            @Valid @RequestHeader BearerTokenDTO bearerTokenDTO,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        Long accountId = accountUtil.getAccountIdFromToken(bearerTokenDTO.getToken());

       GetTransactionsResponse getTransactionsResponse = transactionService.getTransactionByType(accountId, pageNumber, pageSize ,TransactionType.DEPOSIT.name());
        return ResponseEntity.status(HttpStatusCodes.OK).body(getTransactionsResponse);
    }

    @GetMapping("/get/withdraws")
    public ResponseEntity<?> getAllWithdrawTransaction(
            @Valid @RequestHeader BearerTokenDTO bearerTokenDTO,
             @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
             @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        Long accountId = accountUtil.getAccountIdFromToken(bearerTokenDTO.getToken());

        GetTransactionsResponse getTransactionsResponse = transactionService.getTransactionByType(accountId, pageNumber, pageSize, TransactionType.WITHDRAW.name());
        return ResponseEntity.status(HttpStatusCodes.OK).body(getTransactionsResponse);
    }
}
