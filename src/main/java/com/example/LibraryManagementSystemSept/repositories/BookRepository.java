package com.example.LibraryManagementSystemSept.repositories;

import com.example.LibraryManagementSystemSept.enums.Gender;
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

    @Query(value = "select * from book where genre=:genre and cost>:cost",nativeQuery = true)
    List<Book> findByGenreAndCost(String genre,double cost);

    //HQL se
  @Query(value = "select b from Book b where b.genre=:genre and b.cost>:cost")
   List<Book> findByGenderAndGreaterThanCost(Genre genre,double cost);
  @Query(value = "select b from Book b where b.noOfPages>:a and b.noOfPages<:b")
  List<Book> findAllBooksNoOfPagesBetweenAandB(Integer a,Integer b);

}
