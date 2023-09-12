package com.example.LibraryManagementSystemSept.CustomException.TransactionRelatedException;

public class TransactionNotFoundException extends  RuntimeException{
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
