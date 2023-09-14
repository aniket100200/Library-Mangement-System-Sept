package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO;

import com.example.LibraryManagementSystemSept.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponceDto
{
    String title;
    int noOfPages;
    Genre genre;
    double cost;
    String authorName;
}
