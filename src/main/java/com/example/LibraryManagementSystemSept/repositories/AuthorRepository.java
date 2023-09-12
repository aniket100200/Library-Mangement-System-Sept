package com.example.LibraryManagementSystemSept.repositories;

import com.example.LibraryManagementSystemSept.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer>
{

}
