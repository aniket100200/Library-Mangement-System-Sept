package com.example.LibraryManagementSystemSept.CustomException.BookRelatedException;

public class BookIsNotAvailableException extends RuntimeException{
    public BookIsNotAvailableException(String message) {
        super(message);
    }
}
