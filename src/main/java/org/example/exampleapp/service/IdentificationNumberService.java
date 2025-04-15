package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.IdentityCard;
import org.example.exampleapp.repository.IdentificationNumberRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class IdentificationNumberService {

    private final IdentificationNumberRepository identificationNumberRepository;

    public void saveIdentificationNumber(IdentityCard identificationNumber) {

        String trimmedIdentificationNumber = identificationNumber.getIdentificationNumber().trim();
        identificationNumber.setIdentificationNumber(trimmedIdentificationNumber);
        Boolean numberExist = validateIdentificationNumber(trimmedIdentificationNumber);
        if (numberExist) {
            throw new RuntimeException("Identification number already exists");
        }
        setIdentificationInfo(identificationNumber);

        identificationNumberRepository.save(identificationNumber);
    }

    private void setIdentificationInfo(IdentityCard identificationNumber) {
        identificationNumber.setId(UUID.randomUUID().toString());
        identificationNumber.setName(identificationNumber.getName());
        identificationNumber.setLastname(identificationNumber.getLastname());
        identificationNumber.setGender(identificationNumber.getGender());
        identificationNumber.setCreatedDate(new Date());
        identificationNumber.setModifiedDate(new Date());
    }

    public Boolean validateIdentificationNumber(String identificationNumber) {
        IdentityCard identityCard = identificationNumberRepository.findByIdentificationNumber(identificationNumber);
        return !ObjectUtils.isEmpty(identityCard);
    }

}
