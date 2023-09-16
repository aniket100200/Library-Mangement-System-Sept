package com.example.LibraryManagementSystemSept.repositories;

import com.example.LibraryManagementSystemSept.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>
{
}
