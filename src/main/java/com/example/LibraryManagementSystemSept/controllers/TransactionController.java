package com.example.LibraryManagementSystemSept.controllers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;
import com.example.LibraryManagementSystemSept.services.TransactionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionController
{
    @Autowired
    TransactionService transactionService;
    @PostMapping("/issue-book/{studentId}")
    public ResponseEntity issueBook(@RequestParam("bookId") Integer bookId, @PathVariable("studentId") Integer studentId)
    {
        try
        {
            IssueBookResponce responce=transactionService.issueBook(bookId,studentId);
            return new ResponseEntity<>(responce, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("OOP's Something went wrong"+e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return-book")
    public ResponseEntity returnBook(@RequestParam("sId") Integer studentId,@RequestParam("bId")Integer bookId)
    {
        try
        {
            IssueBookResponce responce=transactionService.returnBook(studentId,bookId);

            return new ResponseEntity<>(responce, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            log.error("OOP's Something went wrong"+e.getMessage());
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
