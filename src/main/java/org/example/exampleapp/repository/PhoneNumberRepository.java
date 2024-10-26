package org.example.exampleapp.repository;

import org.example.exampleapp.model.PhoneNumberModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumberModel, UUID> {
    PhoneNumberModel findById(String id);

    boolean findByPhoneNumber(long phoneNumber);
}
