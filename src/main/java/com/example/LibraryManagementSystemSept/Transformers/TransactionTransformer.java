package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.enums.TransactionStatus;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.models.Transaction;

import java.util.UUID;

public class TransactionTransformer
{
    public static Transaction generateTransaction(Student student, Book book)
    {
        return Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .transactionStatus(TransactionStatus.Success)
                .book(book)
                .libraryCard(student.getLibraryCard())
                .build();
    }
}
