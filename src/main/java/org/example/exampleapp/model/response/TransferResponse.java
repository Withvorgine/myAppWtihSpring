package org.example.exampleapp.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransferResponse {
    private double amount;
    private String message;
    private String iban;

}
