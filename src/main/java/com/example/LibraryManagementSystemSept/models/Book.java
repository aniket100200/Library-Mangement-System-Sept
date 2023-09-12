package com.example.LibraryManagementSystemSept.models;

import com.example.LibraryManagementSystemSept.enums.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    int noOfPages;
    @Enumerated(EnumType.STRING)
    Genre genre;

    boolean isAvailable;

    double cost;

    LocalDate dateOfPublication;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    Author author;
}
