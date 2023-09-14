package com.example.LibraryManagementSystemSept.models;

import com.example.LibraryManagementSystemSept.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Author
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;
    int age;
    @Column(unique = true,nullable = false)
    String emailId;

    @UpdateTimestamp
    Date lastActivity;

    @Enumerated(EnumType.STRING)
    Gender gender;


    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    List<Book>books=new ArrayList<>();

    public int getNumberOfBooks()
    {
        return this.books.size();
    }
}
