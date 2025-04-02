package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.IdentificationNumberModel;
import org.example.exampleapp.repository.IdentificationNumberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdentificationNumberService {

    private final IdentificationNumberRepository identificationNumberRepository;

    public void saveIdentificationNumber(IdentificationNumberModel identificationNumber) {
        identificationNumber.setId(UUID.randomUUID().toString());
        String trimmedIdentificationNumber = identificationNumber.getIdentificationNumber().trim();
        identificationNumber.setIdentificationNumber(trimmedIdentificationNumber);
        Boolean numberExist = validateIdentificationNumber(trimmedIdentificationNumber);
        if (!numberExist) {
            identificationNumberRepository.save(identificationNumber);
        } else {
            throw new RuntimeException("Identification number already exists");
        }
    }

    public Boolean validateIdentificationNumber(String identificationNumber) {
        IdentificationNumberModel identificationNumberModel = identificationNumberRepository.findByIdentificationNumber(identificationNumber);
        return !ObjectUtils.isEmpty(identificationNumberModel);
    }

}
