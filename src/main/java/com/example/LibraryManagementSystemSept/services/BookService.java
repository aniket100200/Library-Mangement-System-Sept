package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddBookRequestDTO;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.enums.Genre;

import java.util.List;
import java.util.Set;

public interface BookService
{
    public BookResponceDto addBook(AddBookRequestDTO addBookRequestDTO);
    public String deleteBook(Integer bookId);

    public List<BookResponceDto> getBooksByGenre(Genre genre);

    public List<BookResponceDto> findAllBooksBetweenAandB(Integer a, Integer b);
    public List<BookResponceDto> getBooksByGenreAndMoreThanCost(Genre genre, Integer cost);

    public Set<String> getAllAuthorsByGenre(Genre genre);
}
