package com.example.LibraryManagementSystemSept.CustomException.CardRelaedException;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(String message) {
        super(message);
    }
}
