package org.example.exampleapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberCreateRequest {
    private long phoneNumber;
    private String countryName;
}
