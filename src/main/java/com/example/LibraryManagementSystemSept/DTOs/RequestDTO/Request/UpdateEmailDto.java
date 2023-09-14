package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEmailDto
{
    int authorId;
    String email;
}
