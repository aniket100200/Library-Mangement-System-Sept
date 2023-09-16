package com.example.LibraryManagementSystemSept.services;

import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddStudentDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.StudentResponceDto;
import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.enums.Gender;

import java.util.List;

public interface StudentService
{
    public StudentResponceDto addStudent (AddStudentDto addStudentDto);
    public Department getDeptById(Integer id) throws  Exception;
    public String deleteStudent(Integer regNo) throws Exception;
    public StudentResponceDto updateAge(int age, int regNo);
    public List<StudentResponceDto> getAllMaleStudents(Gender gender);
    public List<StudentResponceDto> getAllStudents();
}
