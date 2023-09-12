package com.example.LibraryManagementSystemSept.services;


import com.example.LibraryManagementSystemSept.CustomException.StudentRelatedException.StudentNotFoundException;
import com.example.LibraryManagementSystemSept.enums.CardStatus;
import com.example.LibraryManagementSystemSept.enums.Department;
import com.example.LibraryManagementSystemSept.enums.Gender;
import com.example.LibraryManagementSystemSept.models.LibraryCard;
import com.example.LibraryManagementSystemSept.models.Student;
import com.example.LibraryManagementSystemSept.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public String addStudent (Student student)
    {
       LibraryCard libraryCard=new LibraryCard();
       libraryCard.setStudent(student);
       libraryCard.setCardId(UUID.randomUUID().toString());
       libraryCard.setCardStatus(CardStatus.Active);

       student.setLibraryCard(libraryCard);  //allocated library_card to the Student..
        studentRepository.save(student); //saved both student and library card.. here because bidirectional mapping.
        return "Student has been added to the database successfully";
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

    public Student updateAge(int age, int regNo)
    {
        Optional<Student>optionalStudent=studentRepository.findById(regNo);
        if(optionalStudent.isEmpty())throw new StudentNotFoundException("Student Not Found Exception");

        Student student=optionalStudent.get();
        student.setAge(age);

       studentRepository.save(student);
        return new Student();
    }

    public List<Student> getAllMaleStudents(Gender gender)
    {

        List<Student>studentList=studentRepository.findStudentByGender(gender);

        return studentList;
    }

    public List<Student> getAllStudents()
    {
        List<Student>list=studentRepository.findAll();


        return list;
    }
}
