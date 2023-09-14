package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO;

import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.models.Student;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponceDto
{
    int reg;
    String name;
    Department department;
    String email;

    String libraryCardId; //this will be uuid..

}
