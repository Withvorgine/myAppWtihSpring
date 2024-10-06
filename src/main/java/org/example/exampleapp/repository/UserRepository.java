package org.example.exampleapp.repository;

import org.example.exampleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
        User findUserByBankAccountNumber(String bankAccountNumber);

        User findUserById(String id);

}
