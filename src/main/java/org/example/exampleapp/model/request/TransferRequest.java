package org.example.exampleapp.model.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TransferRequest {
    private String toIBAN;
    private String fromIBAN;
    private double amount;

}
