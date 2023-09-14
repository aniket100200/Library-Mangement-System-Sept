package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.CustomException.AuthorRelatedException.AuthorNotFoundException;
import com.example.LibraryManagementSystemSept.CustomException.BookRelatedException.BookNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddBookRequestDTO;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.enums.Genre;
import com.example.LibraryManagementSystemSept.models.Author;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.repositories.AuthorRepository;
import com.example.LibraryManagementSystemSept.repositories.BookRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class BookService
{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;
    public BookResponceDto addBook(AddBookRequestDTO addBookRequestDTO)
    {
        Optional<Author>optional=authorRepository.findById(addBookRequestDTO.getAuthorId());

        if(optional.isEmpty())throw new AuthorNotFoundException("Author Not Found!!");
        Author author=optional.get();
            Book book=Book.builder()
                .genre(addBookRequestDTO.getGenre())
                .title(addBookRequestDTO.getTitle())
                .cost(addBookRequestDTO.getPrice())
                .dateOfPublication(addBookRequestDTO.getDataOfPublication())
                .author(author)
                .noOfPages(addBookRequestDTO.getNoOfPage())
                .isAvailable(addBookRequestDTO.isAvailable())
                .build();

        author.getBooks().add(book);
        //save the author
       author = authorRepository.save(author);

        log.info("Book Added successFuyll!!");
       BookResponceDto bookResponceDto=BookResponceDto.builder()
               .authorName(author.getName())
               .genre(addBookRequestDTO.getGenre())
               .title(addBookRequestDTO.getTitle())
               .cost(addBookRequestDTO.getPrice())
               .noOfPages(addBookRequestDTO.getNoOfPage())
               .build();

        return bookResponceDto;
    }

    public String deleteBook(Integer bookId)
    {
        Optional<Book>optional=bookRepository.findById(bookId);
        if(!optional.isPresent())throw new BookNotFoundException("Book did not Found");

        Book book=optional.get();
        Author author=book.getAuthor();

        List<Book> bookList=author.getBooks();
        bookList.remove(book);

        bookRepository.deleteById(bookId);

        authorRepository.save(author);

        return "Book has Been Deleted Successfully From the Database!!";
    }

    public List<BookResponceDto> getBooksByGenre(Genre genre)
    {
        List<Book>books=bookRepository.findByGenre(genre);

        List<BookResponceDto>names=new ArrayList<>();

        for (Book book:books){
            BookResponceDto bookResponceDto=BookResponceDto.builder()
                    .genre(book.getGenre())
                    .authorName(book.getAuthor().getName())
                    .title(book.getTitle())
                    .noOfPages(book.getNoOfPages())
                    .cost(book.getCost()).build();

            names.add(bookResponceDto);
        }
        return names;
    }

    public List<BookResponceDto> getBooksByGenreAndMoreThanCost(Genre genre, Integer cost)
    {
        List<Book>books=bookRepository.findByGenreAndCost(genre.toString(),cost);

        List<BookResponceDto>names=new ArrayList<>();

        for (Book book:books)
        {
            if(book.getCost() > cost)
            {
               BookResponceDto bookResponceDto=BookResponceDto.builder()
                       .authorName(book.getAuthor().getName())
                       .genre(book.getGenre())
                       .cost(book.getCost())
                       .noOfPages(book.getNoOfPages())
                       .title(book.getTitle())
                       .build();
               names.add(bookResponceDto);
            }
        }
        return names;
    }

    public List<BookResponceDto> findAllBooksBetweenAandB(Integer a, Integer b)
    {
        List<Book>bookList=bookRepository.findAll();

        List<BookResponceDto>books=new ArrayList<>();
        for(Book book:bookList)
        {
            int noOfPages= book.getNoOfPages();
            if(noOfPages>a && noOfPages<b)
            {
                BookResponceDto dto=BookResponceDto.builder()
                        .authorName(book.getAuthor().getName())
                        .title(book.getTitle())
                        .genre(book.getGenre())
                        .noOfPages(book.getNoOfPages())
                        .cost(book.getCost())
                        .build();
                books.add(dto);
            }
        }
        if(books.isEmpty()) log.warn("There Are No books Availble");
        return books;
    }

    public Set<String> getAllAuthorsByGenre(Genre genre)
    {
        List<Book>bookList=bookRepository.findByGenre(genre);


       Set<String> collection= new HashSet<>();
        for(Book book:bookList)
        {
           if(!collection.contains(book.getAuthor().getName())) collection.add(book.getAuthor().getName());
        }
        return collection;
    }
}
