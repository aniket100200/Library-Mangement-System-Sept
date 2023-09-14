package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import com.example.LibraryManagementSystemSept.enums.Genre;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddBookRequestDTO
{
     String title;
     boolean isAvailable;
     Genre genre;
     LocalDate dataOfPublication;
     Integer authorId;
     Integer price;
     Integer noOfPage;
}
