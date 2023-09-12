package com.example.LibraryManagementSystemSept.CustomException.BookRelatedException;

public class BookLimitExceededException extends RuntimeException{
    public BookLimitExceededException(String message) {
        super(message);
    }
}
