package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.models.Book;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookTransformer
{
    public static BookResponceDto BookToBookResponceDto(Book book)
    {
    return BookResponceDto.builder()
            .authorName(book.getAuthor().getName())
            .cost(book.getCost())
            .genre(book.getGenre())
            .title(book.getTitle())
            .noOfPages(book.getNoOfPages())
            .build();
    }
}
