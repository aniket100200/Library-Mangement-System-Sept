package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import com.example.LibraryManagementSystemSept.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddAuthorRequestDto
{
    String name;
    int age;
    String email;
    Gender gender;
}
