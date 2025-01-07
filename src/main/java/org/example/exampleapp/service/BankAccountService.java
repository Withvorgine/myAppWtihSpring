package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.BankAccountModel;
import org.example.exampleapp.model.enums.Country;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.example.exampleapp.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountResponse createBankAccount(String bankAccountId, String userId, String country){

        BankAccountModel bankAccountModel = BankAccountModel.builder()
                .id(bankAccountId)
                .userId(userId)
                .country(country)
                .createdDate(new Date())
                .amount(0)
                .build();

        switch (country.toLowerCase()){
            case "turkey":
                bankAccountModel.generateIban(Country.TURKEY.getAbbreviation());
                break;
            case "germany":
                bankAccountModel.generateIban(Country.GERMANY.getAbbreviation());
                break;
            case "spain":
                bankAccountModel.generateIban(Country.SPAIN.getAbbreviation());
                break;
        }

        try {
            bankAccountRepository.save(bankAccountModel);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.warn("Bank account created with Id: {}", bankAccountId);
        return new BankAccountResponse();
    }
}
