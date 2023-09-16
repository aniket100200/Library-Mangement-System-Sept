package com.example.LibraryManagementSystemSept.controllers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.UpdateEmailDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.AuthorResponceDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.services.AuthorService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/author")
@Slf4j
public class AuthorController
{
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public ResponseEntity addAuthor(@RequestBody AddAuthorRequestDto addAuthorRequestDto)
    {
      AuthorResponceDto author= authorService.addAuthor(addAuthorRequestDto);

        return new ResponseEntity(author, HttpStatus.CREATED);
    }


    // update the email id of an author  -->> observer lastActivity column
    @PutMapping("/email")
    public ResponseEntity updateEmail(@RequestBody UpdateEmailDto updateEmailDto)
    {
        try
        {
            AuthorResponceDto responceDto= authorService.updateEmail(updateEmailDto);
            return new ResponseEntity<>(responceDto,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
           log.error("There is some issue"+e.getMessage(),e.getMessage());
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/books-by-author")
    public ResponseEntity booksByAuthor(@RequestParam Integer authorId)
    {
        try
        {
            List<BookResponceDto>books= authorService.findBooksByAuthor(authorId);
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
        List<AuthorResponceDto>authors= authorService.getAllAuthorsHaveWrittenMoreThanXBooks(x);
        return new ResponseEntity(authors,HttpStatus.ACCEPTED);
    }



}
