package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.PhoneNumberModel;
import org.example.exampleapp.model.enums.CountryCode;
import org.example.exampleapp.model.enums.PhoneNumberStatus;
import org.example.exampleapp.model.request.PhoneNumberCreateRequest;
import org.example.exampleapp.model.response.PhoneNumberCreateResponse;
import org.example.exampleapp.model.response.PhoneNumberValidateResponse;
import org.example.exampleapp.repository.PhoneNumberRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneNumberService {

    private final PhoneNumberRepository phoneNumberRepository;
    public PhoneNumberCreateResponse createPhoneNumber(PhoneNumberCreateRequest phoneNumberCreateRequest) {

        String validatePhoneNumber = validatePhoneNumber(phoneNumberCreateRequest);
        System.out.println(validatePhoneNumber);
        PhoneNumberModel phoneNumber = getPhoneNumberModel(phoneNumberCreateRequest);
        switch (phoneNumber.getCountryName()){
            case "TR" :
                phoneNumber.setCountryCode(CountryCode.TURKEY_CODE.getCode());
                break;
            case "ES":
                phoneNumber.setCountryCode(CountryCode.SPAIN_CODE.toString());
        }
        try {
            phoneNumberRepository.save(phoneNumber);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save phone number");
        }

        return getPhoneNumberCreateResponse(phoneNumberCreateRequest);
    }

    private PhoneNumberModel getPhoneNumberModel(PhoneNumberCreateRequest phoneNumberCreateRequest) {
        return PhoneNumberModel.builder()
                .Id(UUID.randomUUID().toString())
                .phoneNumber(phoneNumberCreateRequest.getPhoneNumber())
                .countryName(phoneNumberCreateRequest.getCountryName())
                .phoneNumberStatus(PhoneNumberStatus.AVAILABLE.getPhoneNumberStatus())
                .build();
    }

    private PhoneNumberCreateResponse getPhoneNumberCreateResponse(PhoneNumberCreateRequest phoneNumberCreateRequest) {
        PhoneNumberCreateResponse phoneNumberCreateResponse = new PhoneNumberCreateResponse();
        phoneNumberCreateResponse.setPhoneNumber(phoneNumberCreateRequest.getPhoneNumber());
        phoneNumberCreateResponse.setPhoneNumberMessage("Phone number created: " + phoneNumberCreateRequest.getPhoneNumber());
        return phoneNumberCreateResponse;
    }

    public String validatePhoneNumber(PhoneNumberCreateRequest phoneNumberCreateRequest) {
        PhoneNumberValidateResponse phoneNumberValidateResponse = new PhoneNumberValidateResponse();
        PhoneNumberModel phoneNumberRecord = phoneNumberRepository.findByPhoneNumber(phoneNumberCreateRequest.getPhoneNumber());
        if (!ObjectUtils.isEmpty(phoneNumberRecord)) {
            throw new RuntimeException("Your phone number already exists, phoneNumber: " + phoneNumberRecord.getPhoneNumber());
        }
        phoneNumberValidateResponse.setError("Validation successful you can add your phone number into the database: " + phoneNumberCreateRequest.getPhoneNumber());
        return phoneNumberValidateResponse.getError();
    }
}
