package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.BankAccountModel;
import org.example.exampleapp.model.enums.Country;
import org.example.exampleapp.model.request.TransferRequest;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.example.exampleapp.model.response.TransferResponse;
import org.example.exampleapp.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
        log.info("Bank account created with Id: {}", bankAccountId);
        return new BankAccountResponse();
    }

    public TransferResponse transfer(TransferRequest request) {
        String fromIBAN = request.getFromIBAN();
        String toIBAN = request.getToIBAN();

        List<BankAccountModel> senderAccount = bankAccountRepository.findByIban(fromIBAN);
        List<BankAccountModel> recieverAccount = bankAccountRepository.findByIban(toIBAN);

        double recieverAmount = recieverAccount.getFirst().getAmount();
        String receiverIBAN = recieverAccount.getFirst().getIban();

        if (senderAccount.getFirst().getAmount() < request.getAmount()) {
           throw new RuntimeException("Insufficient balance");
        }

        BigDecimal senderNewAmount = BigDecimal.valueOf(senderAccount.getFirst().getAmount()).subtract(BigDecimal.valueOf(request.getAmount()));
        BigDecimal reieverNewAmount = BigDecimal.valueOf(recieverAmount).add(BigDecimal.valueOf(request.getAmount()));

        try {
            bankAccountRepository.transfer(reieverNewAmount,receiverIBAN);
            bankAccountRepository.transfer(senderNewAmount,fromIBAN);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        TransferResponse transferResponse = new TransferResponse();
        transferResponse.setIban(toIBAN);
        transferResponse.setMessage("Transfer completed successfully");
        transferResponse.setAmount(request.getAmount());
        return transferResponse;
    }
}
