package com.example.LibraryManagementSystemSept.repositories;


import com.example.LibraryManagementSystemSept.enums.Gender;
import com.example.LibraryManagementSystemSept.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer>
{

    List<Student> findStudentByGender(Gender gender);
}
