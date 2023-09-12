package com.example.LibraryManagementSystemSept.CustomException.AuthorRelatedException;

public class AuthorNotFoundException extends RuntimeException
{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
