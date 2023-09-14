package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddStudentDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.StudentResponceDto;
import com.example.LibraryManagementSystemSept.models.Student;

public class StudentTransformer
{
    public  static Student AddStudentDtoToStudent(AddStudentDto addStudentDto)
    {
      return Student.builder()
                .age(addStudentDto.getAge())
                .name(addStudentDto.getName())
                .emailId(addStudentDto.getEmail())
                .department(addStudentDto.getDepartment())
                .gender(addStudentDto.getGender())
                .build();
    }

    public static StudentResponceDto StudentToStudentResponceDto(Student s)
    {
        return StudentResponceDto.builder()
                .reg(s.getRegNo())
                .email(s.getEmailId())
                .libraryCardId(s.getLibraryCard().getCardId())
                .name(s.getName())
                .department(s.getDepartment())
                .build();
    }
}
