package com.example.LibraryManagementSystemSept.services.impl;

import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookIsNotAvailableException;
import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookNotFoundException;
import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookNotIssuedException;
import com.example.LibraryManagementSystemSept.CustomException.StudentRelatedException.StudentNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.IssueBookResponce;
import com.example.LibraryManagementSystemSept.Transformers.BookTransformer;
import com.example.LibraryManagementSystemSept.Transformers.TransactionTransformer;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.models.LibraryCard;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    JavaMailSender  javaMailSender;

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

    SimpleMailMessage   simpleMailMessage=new SimpleMailMessage();
    String text="Hey"+student.getName()+"\n"+", You have successfully Issued the "+book.getTitle()+" book by Author"+book.getAuthor().getName();

    simpleMailMessage.setFrom("khangaraniket365@gmail.com");
    simpleMailMessage.setTo(student.getEmailId());
    simpleMailMessage.setSubject("Congrats!! You have Issued Book"+book.getTitle());

    simpleMailMessage.setText(text);

    javaMailSender.send(simpleMailMessage);



        return issueBookResponce;

    }

    @Override
    public IssueBookResponce returnBook(Integer studentId, Integer bookId)
    {
        //first findStudent Details
        Optional<Student>optionalStudent=studentRepository.findById(studentId);
        if(optionalStudent.isEmpty())
        {
            log.error("Enter a valid Student Id");
            throw new StudentNotFoundException("Student Details are Wrong");
        }

        //check for the book as well..

        Optional<Book>optionalBook=bookRepository.findById(bookId);

        if(optionalBook.isEmpty())
        {
            log.error("Book Id is Invalid");
            throw new BookNotFoundException("book with bookId " + bookId + " Not Found");
        }
        Student student=optionalStudent.get();

        Book book=optionalBook.get();

        //we have book.. let's chekc.

        if(book.isIssued()==false)
        {
            throw new BookNotIssuedException("Book is Not Issued please Enter the valid BookId");
        }
        LibraryCard libraryCard=student.getLibraryCard();

        //we have library card..

        List<Transaction>transactions=libraryCard.getTransactionList();
        boolean wasIssued=false;
        for (Transaction transaction : transactions)
        {
            if(transaction.getBook().getId()==bookId)
            {
                wasIssued=true;
                break;
            }
        }

        if(!wasIssued)throw new BookNotIssuedException("Book was Not Issued by you !! Invalid BookId");

        //just return book;

        Transaction transaction=TransactionTransformer.generateTransaction(student,book);

        //transaction is generated..

        transaction=transactionRepository.save(transaction);

        //just add in LibraryCard..

        student.getLibraryCard().getTransactionList().add(transaction);

        book.getTransactions().add(transaction);

        book.setIssued(false);


        student= studentRepository.save(student);

        book= bookRepository.save(book);


        IssueBookResponce issueBookResponce= BookTransformer.IssueBookResponceFromBookAndStudent(student,book);

        issueBookResponce.setTransactionNumber(transaction.getTransactionId());
        issueBookResponce.setTransactionTime(transaction.getDate());
        issueBookResponce.setTransactionStatus(transaction.getTransactionStatus());

        return  issueBookResponce;
    }


}
