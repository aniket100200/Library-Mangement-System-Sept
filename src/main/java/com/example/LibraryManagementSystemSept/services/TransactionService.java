package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;

public interface TransactionService
{
    public IssueBookResponce issueBook(Integer bookId,Integer studentId);

   public IssueBookResponce returnBook(Integer studentId, Integer bookId);
}
