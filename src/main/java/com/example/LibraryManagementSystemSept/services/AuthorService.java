package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.CustomException.AuthorRelatedException.AuthorNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.UpdateEmailDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.AuthorResponceDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.BookResponceDto;
import com.example.LibraryManagementSystemSept.Transformers.AuthorTransformer;
import com.example.LibraryManagementSystemSept.Transformers.BookTransformer;
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
        //just Transform into Author
        Author author= AuthorTransformer.AuthorFromAddAuthorRequestDto(addAuthorRequestDto);

        Author savedAuthor= authorRepository.save(author);

        AuthorResponceDto responceDto=AuthorTransformer.AuthorToAuthorResponceDto(savedAuthor);

        return responceDto;
    }

    public AuthorResponceDto updateEmail(UpdateEmailDto updateEmailDto)
    {
        Optional<Author>optional=authorRepository.findById(updateEmailDto.getAuthorId());
        if(optional.isPresent()==false)
        {
            log.error("Author with author Id"+updateEmailDto.getAuthorId()+" Not Found!!");
            throw new AuthorNotFoundException("Author Not Found!!");
        }

        Author author=optional.get();

        author.setEmailId(updateEmailDto.getEmail());

       author= authorRepository.save(author);

       AuthorResponceDto authorResponceDto=AuthorTransformer.AuthorToAuthorResponceDto(author);

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
            BookResponceDto bookResponceDto= BookTransformer.BookToBookResponceDto(book);

            //addition of the answerList..
            books.add(bookResponceDto);
        }
        return books;
    }

    public List<AuthorResponceDto> getAllAuthorsHaveWrittenMoreThanXBooks(Integer x)
    {
        List<Author>authorList=authorRepository.findAll();

        List<AuthorResponceDto>authors=new ArrayList<>();
        for(Author author:authorList){
            if(author.getBooks().size()>x)
            {
               AuthorResponceDto authorResponceDto=AuthorTransformer.AuthorToAuthorResponceDto(author);

               //adding of the answer
               authors.add(authorResponceDto);
            }
        }


        return authors;
    }
}
