package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    public BankAccountResponse createBankAccount(String bankAccountId){



        BankAccountResponse bankAccountResponse = new BankAccountResponse();
        return bankAccountResponse;
    }
}
