package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import com.example.LibraryManagementSystemSept.enums.Genre;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
