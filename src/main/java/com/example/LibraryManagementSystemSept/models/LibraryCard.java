package com.example.LibraryManagementSystemSept.models;

import com.example.LibraryManagementSystemSept.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String cardId; //this will be UUID.. you'll have to write the logic for it..

    @Enumerated(EnumType.STRING)
    CardStatus cardStatus;

    @CreationTimestamp
    Date date;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    Student student;
}
