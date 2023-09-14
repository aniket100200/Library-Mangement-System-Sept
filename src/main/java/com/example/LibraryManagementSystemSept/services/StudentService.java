package com.example.LibraryManagementSystemSept.services;


import com.example.LibraryManagementSystemSept.CustomException.StudentRelatedException.StudentNotFoundException;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request.AddStudentDto;
import com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO.StudentResponceDto;
import com.example.LibraryManagementSystemSept.enums.CardStatus;
import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.enums.Gender;
import com.example.LibraryManagementSystemSept.models.LibraryCard;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentResponceDto addStudent (AddStudentDto addStudentDto)
    {

        Student student=new Student(addStudentDto);
       LibraryCard libraryCard=new LibraryCard();
       libraryCard.setStudent(student);
       libraryCard.setCardId(UUID.randomUUID().toString());
       libraryCard.setCardStatus(CardStatus.Active);

       student.setLibraryCard(libraryCard);  //allocated library_card to the Student..
       student= studentRepository.save(student); //saved both student and library card.. here because bidirectional mapping.

        StudentResponceDto studentResponceDto=new StudentResponceDto(student);
        studentResponceDto.setLibraryCardId(student.getLibraryCard().getCardId());
        studentResponceDto.setMessage("Student has been added to the database successfully");

        return studentResponceDto;
    }

    public Department getDeptById(Integer id) throws  Exception{
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

      StudentResponceDto studentResponceDto=new StudentResponceDto(student);
      studentResponceDto.setLibraryCardId(student.getLibraryCard().getCardId());
      studentResponceDto.setMessage("Student with regNo:"+regNo+" has been Updated Successfully!!");
        return studentResponceDto;
    }

    public List<StudentResponceDto> getAllMaleStudents(Gender gender)
    {

        List<Student>studentList=studentRepository.findByGender(gender);

        List<StudentResponceDto>studentResponceDtos=new ArrayList<>();
        for(Student student:studentList)
        {
            StudentResponceDto studentResponceDto=new StudentResponceDto(student);
            studentResponceDto.setLibraryCardId(student.getLibraryCard().getCardId());
            studentResponceDto.setMessage("");
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
            StudentResponceDto studentResponceDto=StudentResponceDto.builder()
                            .name(student.getName())
                                    .email(student.getEmailId())
                                            .reg(student.getRegNo())
                                                    .department(student.getDepartment())
                                                            .libraryCardId(student.getLibraryCard().getCardId())
                                                                    .message("Good Student !!")
                                                                            .build();
            studentResponceDtos.add(studentResponceDto);
        }

        return studentResponceDtos;
    }
}
