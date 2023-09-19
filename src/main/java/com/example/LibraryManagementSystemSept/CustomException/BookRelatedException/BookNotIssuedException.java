package com.example.LibraryManagementSystemSept.CustomException.BookRelatedException;

public class BookNotIssuedException extends  RuntimeException{
    public BookNotIssuedException(String message) {
        super(message);
    }
}
