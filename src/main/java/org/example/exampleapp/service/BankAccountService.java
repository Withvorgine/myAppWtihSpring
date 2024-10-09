package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.BankAccountModel;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.example.exampleapp.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountResponse createBankAccount(String bankAccountId, String userId){

        BankAccountModel bankAccountModel = new BankAccountModel();
        bankAccountModel.setUserId(userId);
        bankAccountModel.setId(bankAccountId);
        bankAccountModel.setIban(UUID.randomUUID().toString());

        bankAccountRepository.save(bankAccountModel);
        log.info("Bank account created with Id: {}", bankAccountId);
        BankAccountResponse bankAccountResponse = new BankAccountResponse();
        return bankAccountResponse;
    }
}
