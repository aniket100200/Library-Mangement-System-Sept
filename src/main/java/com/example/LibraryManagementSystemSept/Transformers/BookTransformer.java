package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddBookRequestDTO;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.models.Transaction;
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

    public static Book AddBookRequestDtoToBook(AddBookRequestDTO addBookRequestDTO)
    {
        return  Book.builder()
                .genre(addBookRequestDTO.getGenre())
                .title(addBookRequestDTO.getTitle())
                .cost(addBookRequestDTO.getPrice())
                .dateOfPublication(addBookRequestDTO.getDataOfPublication())
                .noOfPages(addBookRequestDTO.getNoOfPage())
                .issued(addBookRequestDTO.isAvailable())
                .build();
    }

    public static IssueBookResponce IssueBookResponceFromBookAndStudent(Student student, Book book)
    {
        return IssueBookResponce.builder()
                .bookName(book.getTitle())
                .studentName(student.getName())
                .libraryCardNumber(student.getLibraryCard().getCardId())
                .authorName(book.getAuthor().getName())
                .build();
    }
}
