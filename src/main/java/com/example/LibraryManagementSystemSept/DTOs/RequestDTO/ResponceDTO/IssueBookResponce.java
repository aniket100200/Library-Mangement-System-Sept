package com.example.LibraryManagementSystemSept.DTOs.RequestDTO.ResponceDTO;

import com.example.LibraryManagementSystemSept.enums.TransactionStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IssueBookResponce
{
    String transactionNumber;
    Date transactionTime;

    TransactionStatus transactionStatus;

    String bookName;
    String authorName;
    String studentName;

    String libraryCardNumber;
}
