package org.example.exampleapp.model;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModelRequest {
    private String name;
    private String lastname;
    private String email;
    private String phoneNo;
    private String amount;
    private String bankAccountNumber;
    private String IBAN;
}
