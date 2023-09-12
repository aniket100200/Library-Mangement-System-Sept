package com.example.LibraryManagementSystemSept.CustomException.TransactionRelatedException;

public class TrasactionFailedDueToCardblock extends Exception
{
    public TrasactionFailedDueToCardblock(String message)
    {
        super(message);
    }
}
