package org.example.exampleapp.model.request;

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
    private String bankAccountNumber;
    private String country;
}
