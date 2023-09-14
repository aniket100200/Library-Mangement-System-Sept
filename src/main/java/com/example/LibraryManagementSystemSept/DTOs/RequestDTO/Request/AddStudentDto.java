package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentDto
{
    String name;
    Department department;
    String email;
    int age;
    Gender gender;

}
