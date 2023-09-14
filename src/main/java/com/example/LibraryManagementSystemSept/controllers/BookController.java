package com.example.LibraryManagementSystemSept.controllers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddBookRequestDTO;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.enums.Genre;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.services.BookService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/book")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class BookController
{
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody AddBookRequestDTO addBookRequestDTO)
    {
        BookResponceDto responce= bookService.addBook(addBookRequestDTO);

        return new ResponseEntity(responce, HttpStatus.CREATED);
    }

    // delete a book

    @DeleteMapping("/delete")
    public ResponseEntity deleteBookById(@RequestParam Integer bookId)
    {
       try
       {
           String res=bookService.deleteBook(bookId);
           return new ResponseEntity(res,HttpStatus.ACCEPTED);
       }
       catch (Exception e){
           log.error("There is Some error"+e.getMessage());

           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    // give me names of all the books of a particular genre

    @GetMapping("/get-books-by-genre")
    public List<BookResponceDto> getBooksByGenre(@RequestParam Genre genre)
    {
        List<BookResponceDto>books=bookService.getBooksByGenre(genre);
        return books;
    }

    // give me names of all the books of a particular genre and cost gretaer than 500 rs

    @GetMapping("/get-books-by-genre-and-more-than/{cost}")
    public List<BookResponceDto>getBooksByGenreAndMoreThanCost(@RequestParam Genre genre, @PathVariable("cost") Integer cost){
        List<BookResponceDto>ans=bookService.getBooksByGenreAndMoreThanCost(genre,cost);
        return ans;
    }

    // give me all the books having number of pages between 'a' and 'b'

    @GetMapping("/get-all-books-having-pages-between")
    public List<BookResponceDto>findAllBooksBetweenAandB(@RequestParam Integer a, @RequestParam Integer b)
    {
        return  bookService.findAllBooksBetweenAandB(a,b);
    }

    // give me the names of all the authors who write a particular genre

    @GetMapping("/get-all-authors-by-genre")
    public Collection getAllAuthorsByGenre(@RequestParam Genre genre)
    {
        return bookService.getAllAuthorsByGenre(genre);
    }

}
