package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.BankAccountModel;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.example.exampleapp.repository.BankAccountRepository;
import org.example.exampleapp.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bankAccount")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;
    private final BankAccountRepository accountRepository;

    @GetMapping("{bankAccountId}")
    public ResponseEntity<BankAccountResponse> createBankAccount(@PathVariable String bankAccountId,String userId,String country) {
        BankAccountResponse response = bankAccountService.createBankAccount(bankAccountId, userId, country);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "findBankAccount/{findBankAccountId}", produces = {"application/json;charset=utf-8"})
    public ResponseEntity<BankAccountModel> getBankAccount(@PathVariable("findBankAccountId") String id)  {
        return new ResponseEntity<>(accountRepository.findById(id), HttpStatus.OK);
    }
}
