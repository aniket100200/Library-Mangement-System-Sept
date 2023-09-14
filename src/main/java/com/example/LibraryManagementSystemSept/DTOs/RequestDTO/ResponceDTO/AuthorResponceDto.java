package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO;

import com.example.LibraryManagementSystemSept.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorResponceDto
{
    String name;
    int age;
    String email;
    Date lastActivity;
    Gender gender;
}
