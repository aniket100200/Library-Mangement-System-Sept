package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.Request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateEmailDto
{
    int authorId;
    String email;
}
