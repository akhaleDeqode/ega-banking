package com.ega.banking.error;

import com.ega.banking.constants.ErrorMessages;
import com.ega.banking.constants.HttpStatusCodes;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST).body(ErrorMessages.INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(InvalidAccountIdException.class)
    public ResponseEntity<?> handleInvalidAccountIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST).body(ErrorMessages.INVALID_ACCOUNT_ID_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(InvalidBankIdException.class)
    public ResponseEntity<?> handleInvalidBankIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST).body(ErrorMessages.INVALID_BANK_ID_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<?> handleInvalidUserIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST).body(ErrorMessages.INVALID_USER_ID_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(InvalidTransactionTypeException.class)
    public ResponseEntity<?> handleInvalidTransactionTypeException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST).body(ErrorMessages.INVALID_TRANSACTION_TYPE_EXCEPTION_MESSAGE);
    }
}
