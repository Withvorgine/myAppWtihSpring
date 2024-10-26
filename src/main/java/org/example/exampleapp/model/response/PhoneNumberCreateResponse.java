package org.example.exampleapp.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PhoneNumberCreateResponse {
    private long phoneNumber;
    private String countryCode;
    private String phoneNumberMessage;

}
