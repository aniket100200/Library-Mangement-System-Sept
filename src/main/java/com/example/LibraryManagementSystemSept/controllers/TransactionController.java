package com.example.LibraryManagementSystemSept.controllers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController
{
    @PostMapping("/issue-book/{studentId}")
    public IssueBookResponce issueBook(@RequestParam("bookId") Integer bookId, @PathVariable("studentId") Integer studentId)
    {
        try
        {

        }
        catch (Exception e)
        {

        }
    }
}
