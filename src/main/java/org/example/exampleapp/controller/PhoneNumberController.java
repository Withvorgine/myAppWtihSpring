package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.PhoneNumberModel;
import org.example.exampleapp.model.request.PhoneNumberCreateRequest;
import org.example.exampleapp.model.response.PhoneNumberCreateResponse;
import org.example.exampleapp.repository.PhoneNumberRepository;
import org.example.exampleapp.service.PhoneNumberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phoneNumber")
public class PhoneNumberController {

    private final PhoneNumberService phoneNumberService;
    private final PhoneNumberRepository phoneNumberRepository;

    @PostMapping("/createPhoneNumber")
    public ResponseEntity<PhoneNumberCreateResponse> createPhoneNumber(@RequestBody PhoneNumberCreateRequest request) {
        PhoneNumberCreateResponse response = phoneNumberService.createPhoneNumber(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<PhoneNumberModel> getPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return new ResponseEntity<>(phoneNumberRepository.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
}
