package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.CustomException.AuthorRelatedException.AuthorNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.UpdateEmailDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.AuthorResponceDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.models.Author;
import com.example.LibraryManagementSystemSept.models.Book;
import com.example.LibraryManagementSystemSept.repositories.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;
    public AuthorResponceDto addAuthor(AddAuthorRequestDto addAuthorRequestDto)
    {
        Author author=Author.builder()
                .age(addAuthorRequestDto.getAge())
                .emailId(addAuthorRequestDto.getEmail())
                .gender(addAuthorRequestDto.getGender())
                .name(addAuthorRequestDto.getName())
                .books(new ArrayList<>())
                .build();
      Author savedAuthor= authorRepository.save(author);

        AuthorResponceDto responceDto=AuthorResponceDto.builder()
                .age(author.getAge())
                .email(author.getEmailId())
                .name(savedAuthor.getName())
                .gender(author.getGender())
                .lastActivity(author.getLastActivity())
                .build();

        return responceDto;
    }

    public AuthorResponceDto updateEmail(UpdateEmailDto updateEmailDto)
    {
        Optional<Author>author=authorRepository.findById(updateEmailDto.getAuthorId());
        if(author.isPresent()==false)
        {
            log.error("Author with author Id"+updateEmailDto.getAuthorId()+" Not Found!!");
            throw new AuthorNotFoundException("Author Not Found!!");
        }

        Author author1=author.get();
        author1.setEmailId(updateEmailDto.getEmail());

       author1= authorRepository.save(author1);

       AuthorResponceDto authorResponceDto=AuthorResponceDto.builder()
               .age(author1.getAge())
               .name(author1.getName())
               .email(author1.getEmailId())
               .lastActivity(author1.getLastActivity())
               .gender(author1.getGender())
               .message("Author Has Been updated Successfully").build();

        return authorResponceDto;
    }

    public List<BookResponceDto> findBooksByAuthor(Integer authorId)
    {
        Optional<Author>optional=authorRepository.findById(authorId);
        if(optional.isPresent()==false)
        {
            log.error("Author with author Id"+authorId+" Not Found!!");
            throw new AuthorNotFoundException("Author Not Found!!");
        }

        Author author=optional.get();
       //you have to do everything here it self..

        List<BookResponceDto>books=new ArrayList<>();
        for(Book book:author.getBooks())
        {
            BookResponceDto bookResponceDto=BookResponceDto.builder()
                    .authorName(book.getAuthor().getName())
                    .cost(book.getCost())
                    .genre(book.getGenre())
                    .title(book.getTitle())
                    .noOfPages(book.getNoOfPages()).build();

            books.add(bookResponceDto);
        }
        return books;
    }

    public List<AuthorResponceDto> getAllAuthorsHaveWrittenMoreThanXBooks(Integer x)
    {
        List<Author>authorList=authorRepository.findAll();

        List<AuthorResponceDto>authors=new ArrayList<>();
        for(Author author:authorList){
            if(author.getBooks().size()>x){
               AuthorResponceDto authorResponceDto=AuthorResponceDto.builder()
                       .name(author.getName())
                       .age(author.getAge())
                       .message("Happy")
                       .email(author.getEmailId())
                       .gender(author.getGender())
                       .lastActivity(author.getLastActivity()).build();
               authors.add(authorResponceDto);
            }
        }


        return authors;
    }
}
