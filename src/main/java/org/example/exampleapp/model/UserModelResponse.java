package org.example.exampleapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModelResponse {
    private String name;
    private String lastname;
    private String email;
    private String phoneNo;
    private String amount;
    private String bankAccountNumber;
    private String IBAN;
}