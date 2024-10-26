package org.example.exampleapp.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PhoneNumberValidateResponse {
    long phoneNumber;
    String error;
}
