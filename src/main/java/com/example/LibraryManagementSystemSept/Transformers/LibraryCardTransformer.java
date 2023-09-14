package com.example.LibraryManagementSystemSept.Transformers;

import com.example.LibraryManagementSystemSept.enums.CardStatus;
import com.example.LibraryManagementSystemSept.models.LibraryCard;
import com.example.LibraryManagementSystemSept.models.Student;

import java.util.UUID;

public class LibraryCardTransformer
{
    public  static LibraryCard GenerateLibraryCardForStudent(Student student)
    {
        return LibraryCard.builder()
                .student(student)
                .cardId(UUID.randomUUID().toString())
                .cardStatus(CardStatus.Active)
                .build();

    }
}
