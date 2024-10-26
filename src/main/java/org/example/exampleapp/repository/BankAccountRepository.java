package org.example.exampleapp.repository;

import org.example.exampleapp.model.BankAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccountModel, UUID> {
        BankAccountModel findById(String id);
}
