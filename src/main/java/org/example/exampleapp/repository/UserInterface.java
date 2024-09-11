package org.example.exampleapp.repository;

import org.example.exampleapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserInterface extends JpaRepository<User, UUID> {
        User findUserByIBAN(String iban);
}
