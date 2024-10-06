package org.example.exampleapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountResponse {
    private String accountNumberId;
    private String responseMessage;
}
