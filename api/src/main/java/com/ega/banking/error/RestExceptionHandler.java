package com.ega.banking.error;

import com.ega.banking.constants.ApplicationConstants;
import com.ega.banking.constants.ErrorMessages;
import com.ega.banking.constants.HttpStatusCodes;
import com.ega.banking.dto.ExcetionDto;
import com.ega.banking.dto.ValidationErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException() {
        return ResponseEntity.status(HttpStatusCodes.PAYMENT_REQUIRED)
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(Exception ex) {
        if (ex.getMessage().contains("Duplicate entry")) {
            return ResponseEntity.status(HttpStatusCodes.CONFLICT)
                    .body(ExcetionDto.builder().message(ErrorMessages.USER_ALREADY_EXIST)
                            .build());

        }

        return ResponseEntity.status(HttpStatusCodes.CONFLICT)
                .body(ExcetionDto.builder().message(ex.getMessage())
                        .build());
    }

    @ExceptionHandler(UserNotExistException.class)
    public ResponseEntity<?> handleUserNotExistException(Exception ex) {
        return ResponseEntity.status(HttpStatusCodes.NOT_FOUND)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_EMAIL)
                        .build());
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<?> handleWrongPasswordException(Exception ex) {
        return ResponseEntity.status(HttpStatusCodes.UNAUTHORIZED)
                .body(ExcetionDto.builder().message(ErrorMessages.INCORRECT_PASSWORD)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();

        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder()
                        .message(ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage())
                        .build());
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<?> handleInvalidAmountException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ErrorMessages.INVALID_AMOUNT)
                        .build());
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<?> handleWeakPasswordException() {
        return ResponseEntity.status(HttpStatusCodes.BAD_REQUEST)
                .body(ExcetionDto.builder().message(ApplicationConstants.WEAK_PASSWORD)
                        .build());
    }
}
