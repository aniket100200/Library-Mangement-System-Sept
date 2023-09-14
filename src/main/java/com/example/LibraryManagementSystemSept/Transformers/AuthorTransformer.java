package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddAuthorRequestDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.AuthorResponceDto;
import com.example.LibraryManagementSystemSept.models.Author;

import java.util.ArrayList;

public class AuthorTransformer
{

    public static Author AuthorFromAddAuthorRequestDto(AddAuthorRequestDto addAuthorRequestDto){
        return Author.builder()
                .age(addAuthorRequestDto.getAge())
                .emailId(addAuthorRequestDto.getEmail())
                .gender(addAuthorRequestDto.getGender())
                .name(addAuthorRequestDto.getName())
                .books(new ArrayList<>())
                .build();
    }

    public static AuthorResponceDto AuthorToAuthorResponceDto(Author author)
    {
        return AuthorResponceDto.builder()
                .age(author.getAge())
                .email(author.getEmailId())
                .name(author.getName())
                .gender(author.getGender())
                .lastActivity(author.getLastActivity())
                .build();
    }
}
