package com.example.LibraryManagementSystemSept.controllers;

import com.example.LibraryManagementSystemSept.RequestDTO.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.RequestDTO.UpdateEmailDto;
import com.example.LibraryManagementSystemSept.enums.Gender;
import com.example.LibraryManagementSystemSept.enums.Genre;
import com.example.LibraryManagementSystemSept.models.Author;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@Slf4j
public class AuthorController
{
    @Autowired
    private AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto)
    {
        Author responce=authorService.addAuthor(addAuthorRequestDto);
        return new ResponseEntity(responce, HttpStatus.CREATED);
    }






    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/email")
    public ResponseEntity updateEmail(@RequestBody UpdateEmailDto updateEmailDto)
    {
        try {
            String ans=authorService.updateEmail(updateEmailDto);
            return new ResponseEntity<>(ans,HttpStatus.CREATED);
        }
        catch (Exception e){
           log.error("There is some issue"+e.getMessage(),e.getMessage());
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books-by-author")
    public ResponseEntity booksByAuthor(@RequestParam Integer authorId)
    {
        try
        {
            List<Book>books=authorService.findBooksByAuthor(authorId);
            return new ResponseEntity<>(books,HttpStatus.ACCEPTED);
        }catch (Exception e)
        {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    // give me the names of authors who have written more than 'x' number of books

    @GetMapping("/author-Written/{x}")
    public ResponseEntity getAllAuthorsHaveWrittenMoreThanXBooks(@PathVariable("x") Integer x)
    {
        List<Author>authors=authorService.getAllAuthorsHaveWrittenMoreThanXBooks(x);
        return new ResponseEntity(authors,HttpStatus.ACCEPTED);
    }



}
