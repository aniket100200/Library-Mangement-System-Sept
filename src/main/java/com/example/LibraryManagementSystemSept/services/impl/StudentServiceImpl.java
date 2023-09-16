package com.example.LibraryManagementSystemSept.services.impl;


import com.example.LibraryManagementSystemSept.CustomException.StudentRelatedException.StudentNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddStudentDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.StudentResponceDto;
import com.example.LibraryManagementSystemSept.Transformers.LibraryCardTransformer;
import com.example.LibraryManagementSystemSept.Transformers.StudentTransformer;
import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.enums.Gender;
import com.example.LibraryManagementSystemSept.models.LibraryCard;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.repositories.StudentRepository;
import com.example.LibraryManagementSystemSept.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentResponceDto addStudent (AddStudentDto addStudentDto)
    {

        Student student= StudentTransformer.AddStudentDtoToStudent(addStudentDto);

        LibraryCard libraryCard= LibraryCardTransformer.GenerateLibraryCardForStudent(student);

         student.setLibraryCard(libraryCard);  //allocated library_card to the Student..

         student= studentRepository.save(student); //saved both student and library card.. here because bidirectional mapping.

        StudentResponceDto studentResponceDto=StudentTransformer.StudentToStudentResponceDto(student);

        return studentResponceDto;
    }

    public Department getDeptById(Integer id) throws  Exception
    {
        Optional<Student>optionalStudent=studentRepository.findById(id);
        if(optionalStudent.isPresent()==false)throw new Exception("Student with Id is not found");

        Student student=optionalStudent.get();
        return student.getDepartment();
    }

    public String deleteStudent(Integer regNo) throws Exception
    {
        Optional<Student>optionalStudent=studentRepository.findById(regNo);
        if(optionalStudent.isEmpty())throw new StudentNotFoundException("Student Not Found Exception");

        studentRepository.delete(optionalStudent.get());

        return "Student Delete Successfully!!";
    }

    public StudentResponceDto updateAge(int age, int regNo)
    {
        Optional<Student>optionalStudent=studentRepository.findById(regNo);
        if(optionalStudent.isEmpty())throw new StudentNotFoundException("Student Not Found Exception");

        Student student=optionalStudent.get();
        student.setAge(age);

      student= studentRepository.save(student);

      StudentResponceDto studentResponceDto=StudentTransformer.StudentToStudentResponceDto(student);

        return studentResponceDto;
    }

    public List<StudentResponceDto> getAllMaleStudents(Gender gender)
    {

        List<Student>studentList=studentRepository.findByGender(gender);

        List<StudentResponceDto>studentResponceDtos=new ArrayList<>();
        for(Student student:studentList)
        {
            StudentResponceDto studentResponceDto=StudentTransformer.StudentToStudentResponceDto(student);

            studentResponceDtos.add(studentResponceDto);
        }

        return studentResponceDtos;
    }

    public List<StudentResponceDto> getAllStudents()
    {
        List<Student>studentList=studentRepository.findAll();

        List<StudentResponceDto>studentResponceDtos=new ArrayList<>();
        for(Student student:studentList)
        {
            StudentResponceDto studentResponceDto=StudentTransformer.StudentToStudentResponceDto(student);
            studentResponceDtos.add(studentResponceDto);
        }
        return studentResponceDtos;
    }
}
