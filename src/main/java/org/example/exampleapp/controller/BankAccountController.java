package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.response.BankAccountResponse;
import org.example.exampleapp.service.BankAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bankAccount")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("{bankAccountId}")
    public ResponseEntity<BankAccountResponse> createBankAccount(@PathVariable String bankAccountId) {
        BankAccountResponse response = bankAccountService.createBankAccount(bankAccountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
