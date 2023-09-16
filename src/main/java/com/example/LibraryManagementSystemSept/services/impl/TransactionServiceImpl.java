package com.example.LibraryManagementSystemSept.services.impl;

import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookIsNotAvailableException;
import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookNotFoundException;
import com.example.LibraryManagementSystemSept.CustomException.StudentRelatedException.StudentNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;
import com.example.LibraryManagementSystemSept.Transformers.BookTransformer;
import com.example.LibraryManagementSystemSept.Transformers.TransactionTransformer;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.models.Transaction;
import com.example.LibraryManagementSystemSept.repositories.BookRepository;
import com.example.LibraryManagementSystemSept.repositories.StudentRepository;
import com.example.LibraryManagementSystemSept.repositories.TransactionRepository;
import com.example.LibraryManagementSystemSept.services.TransactionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionServiceImpl implements TransactionService
{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public IssueBookResponce issueBook(Integer bookId, Integer studentId)
    {
        //first check for the student
        Optional<Student>optionalStudent=studentRepository.findById(studentId);
        if(!optionalStudent.isPresent())
        {
            log.error("Student id is Invalid!!");
            throw new StudentNotFoundException("There no Student Found with studentId" + studentId);
        }



        //then validate the book..

        Optional<Book>optionalBook=bookRepository.findById(bookId);

        if(optionalBook.isEmpty())
        {
            log.error("Book Id is Invalid");
            throw new BookNotFoundException("book with bookId " + bookId + " Not Found");
        }


        Book book=optionalBook.get();


        if(book.isIssued())
        {
            throw new BookIsNotAvailableException("Book is Issued by SomeOne!!");
        }

        Student student=optionalStudent.get();



        //I've the student and the book let's do the Transaction ..
        Transaction transaction= TransactionTransformer.generateTransaction(student,book);

        //let's save this transaction first..

        Transaction savedTransaction= transactionRepository.save(transaction);

        //let's add this transaction into Libararycard and Book..

        student.getLibraryCard().getTransactionList().add(savedTransaction);

        //also add in & set isAvailble fale or issued true;
         book.setIssued(true);

        book.getTransactions().add(savedTransaction);


        //save the book as well as student.
      student= studentRepository.save(student);

       book= bookRepository.save(book);


    IssueBookResponce issueBookResponce= BookTransformer.IssueBookResponceFromBookAndStudent(student,book);

    issueBookResponce.setTransactionNumber(transaction.getTransactionId());
    issueBookResponce.setTransactionTime(transaction.getDate());
    issueBookResponce.setTransactionStatus(transaction.getTransactionStatus());
    return issueBookResponce;

    }
}
