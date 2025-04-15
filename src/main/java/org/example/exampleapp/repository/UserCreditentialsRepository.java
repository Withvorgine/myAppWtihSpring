package org.example.exampleapp.repository;

import org.example.exampleapp.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserCreditentialsRepository extends JpaRepository<UserCredentials, UUID> {
    List<UserCredentials> findByUsername(String username);

    @Query("SELECT count(*) from UserCredentials")
    int countUsers();

}
