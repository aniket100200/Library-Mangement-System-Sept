package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.UpdateEmailDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.AuthorResponceDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;

import java.util.List;

public interface AuthorService
{
    public AuthorResponceDto addAuthor(AddAuthorRequestDto addAuthorRequestDto);
    public AuthorResponceDto updateEmail(UpdateEmailDto updateEmailDto);
    public List<BookResponceDto> findBooksByAuthor(Integer authorId);
    public List<AuthorResponceDto> getAllAuthorsHaveWrittenMoreThanXBooks(Integer x);
}
