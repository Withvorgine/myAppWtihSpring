package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.request.UserModelRequest;
import org.example.exampleapp.model.response.UserModelResponse;
import org.example.exampleapp.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;
    private final BankAccountService bankAccountService;

    public UserModelResponse createUser(UserModelRequest request) {

        User user = User.builder()
                .name(request.getName())
                .bankAccountNumber(request.getBankAccountNumber())
                .email(request.getEmail())
                .lastname(request.getLastname())
                .phoneNo(request.getPhoneNo())
                .country(request.getCountry())
                .createdDate(new Date())
                .build();

        UserModelResponse userModelResponse = new UserModelResponse();

        try {
            user.generateBankAccountNumber();
            user.generateId();
            log.info("User created: {}", user.getId());

            //Customer creation
            userRepository.save(user);
        } catch (Exception e) {
            userModelResponse.setResponseMessage("Error while creating user");
        }
        try {
            //Posting bankAccountService to create bank account
            bankAccountService.createBankAccount(user.getBankAccountNumber(),user.getId(),user.getCountry());
        } catch (Exception e) {
            userModelResponse.setResponseMessage("Bank account creation failed");
        }
        userModelResponse.setId(user.getId());
        userModelResponse.setResponseMessage("customer successfully created");
        return userModelResponse;
    }
}
