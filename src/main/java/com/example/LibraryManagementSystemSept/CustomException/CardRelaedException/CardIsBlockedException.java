package com.example.LibraryManagementSystemSept.CustomException.CardRelaedException;

public class CardIsBlockedException extends RuntimeException{
    public CardIsBlockedException(String message)
    {
        super(message);
    }
}
