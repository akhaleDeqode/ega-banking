package com.ega.banking.constants;

public class ErrorMessages {

    public static final String INSUFFICIENT_BALANCE_EXCEPTION_MESSAGE = "Insufficient Balance";
    public static final String INVALID_ACCOUNT_ID_EXCEPTION_MESSAGE = "Invalid account id";
    public static final String INVALID_BANK_ID_EXCEPTION_MESSAGE = "Invalid bank id";
    public static final String INVALID_USER_ID_EXCEPTION_MESSAGE = "Invalid user id";
    public static final String INVALID_TRANSACTION_TYPE_EXCEPTION_MESSAGE = "Invalid transaction";
    public static final String INVALID_AMOUNT = "Amount must be more than 1 and less than 1000000";
    public static final String INVALID_EMAIL = "User does not exist";
    public static final String INCORRECT_PASSWORD = "Wrong Password";
    public static final String UNAUTHORISED_USER = "User not authorize, Please login again";
    public static final String USER_ALREADY_EXIST = "User already exist";

    private ErrorMessages() {}
}
