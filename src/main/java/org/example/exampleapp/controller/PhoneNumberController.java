package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.request.PhoneNumberCreateRequest;
import org.example.exampleapp.model.response.PhoneNumberCreateResponse;
import org.example.exampleapp.service.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;

    @PostMapping("/createPhoneNumber")
    public ResponseEntity<PhoneNumberCreateResponse> createPhoneNumber(@RequestBody PhoneNumberCreateRequest request) {
        try {
            PhoneNumberCreateResponse response = phoneNumberService.createPhoneNumber(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException e) {
           throw  e;
        }

    }
}
