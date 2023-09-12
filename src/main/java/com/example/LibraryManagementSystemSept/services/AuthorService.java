package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.CustomException.AuthorRelatedException.AuthorNotFoundException;
import com.example.LibraryManagementSystemSept.RequestDTO.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.RequestDTO.UpdateEmailDto;
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
    public Author addAuthor(AddAuthorRequestDto addAuthorRequestDto)
    {
        Author author=Author.builder()
                .age(addAuthorRequestDto.getAge())
                .emailId(addAuthorRequestDto.getEmail())
                .gender(addAuthorRequestDto.getGender())
                .name(addAuthorRequestDto.getName())
                .books(new ArrayList<>())
                .build();
      Author savedAuthor=  authorRepository.save(author);

        return savedAuthor;
    }

    public String updateEmail(UpdateEmailDto updateEmailDto)
    {
        Optional<Author>author=authorRepository.findById(updateEmailDto.getAuthorId());
        if(author.isPresent()==false) {
            log.error("Author with author Id"+updateEmailDto.getAuthorId()+" Not Found!!");
            throw new AuthorNotFoundException("Author Not Found!!");
        }

        Author author1=author.get();
        author1.setEmailId(updateEmailDto.getEmail());
        authorRepository.save(author1);

        return "Author Has Been updated Successfully";
    }

    public List<Book> findBooksByAuthor(Integer authorId)
    {
        Optional<Author>optional=authorRepository.findById(authorId);
        if(optional.isPresent()==false)
        {
            log.error("Author with author Id"+authorId+" Not Found!!");
            throw new AuthorNotFoundException("Author Not Found!!");
        }

        Author author=optional.get();
        return author.getBooks();
    }

    public List<Author> getAllAuthorsHaveWrittenMoreThanXBooks(Integer x)
    {
        List<Author>authorList=authorRepository.findAll();

        List<Author>authors=new ArrayList<>();
        for(Author author:authorList){
            if(author.getBooks().size()>x){
                authors.add(author);
            }
        }
        return authors;
    }
}
