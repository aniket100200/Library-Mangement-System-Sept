package com.example.LibraryManagementSystemSept.repositories;

import com.example.LibraryManagementSystemSept.enums.Genre;
import com.example.LibraryManagementSystemSept.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer>
{

    List<Book> findByGenre(Genre genre);


}
