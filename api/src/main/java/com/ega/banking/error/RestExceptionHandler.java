package com.ega.banking.error;

import com.ega.banking.constants.ErrorMessages;
import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.dto.ExcetionDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE)
                        .build());
    }

    @ExceptionHandler(InvalidAccountIdException.class)
    public ResponseEntity<?> handleInvalidAccountIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_ACCOUNT_ID_EXCEPTION_MESSAGE)
                        .build());
    }

    @ExceptionHandler(InvalidBankIdException.class)
    public ResponseEntity<?> handleInvalidBankIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_BANK_ID_EXCEPTION_MESSAGE)
                        .build());
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<?> handleInvalidUserIdException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_USER_ID_EXCEPTION_MESSAGE)
                        .build());
    }

    @ExceptionHandler(InvalidTransactionTypeException.class)
    public ResponseEntity<?> handleInvalidTransactionTypeException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_TRANSACTION_TYPE_EXCEPTION_MESSAGE)
                        .build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_TRANSACTION_TYPE_EXCEPTION_MESSAGE)
                        .build());
    }
}
