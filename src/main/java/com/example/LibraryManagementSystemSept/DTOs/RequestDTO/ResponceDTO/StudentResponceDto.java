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

    String message;

    public StudentResponceDto(Student student)
    {
        this.reg= student.getRegNo();
        name=student.getName();
        department=student.getDepartment();
        email=student.getEmailId();
    }

    String libraryCardId; //this will be uuid..

}
