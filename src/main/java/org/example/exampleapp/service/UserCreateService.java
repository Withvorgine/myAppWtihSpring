package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.request.UserModelRequest;
import org.example.exampleapp.model.response.UserModelResponse;
import org.example.exampleapp.repository.BankAccountRepository;
import org.example.exampleapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final WebClient webClient;

    public UserModelResponse createUser(UserModelRequest request) {

        User user = User.builder()
                .name(request.getName())
                .bankAccountNumber(request.getBankAccountNumber())
                .email(request.getEmail())
                .lastname(request.getLastname())
                .phoneNo(request.getPhoneNo())
                .build();

        UserModelResponse userModelResponse = new UserModelResponse();

        try {
            user.generateBankAccountNumber();
            user.generateId();
            userRepository.save(user);

            //Posting create api another controller to create bank account
            String url = "http://localhost:8080/api/bankAccount/" + user.getBankAccountNumber();
            webClient.get()
                    .uri(url);

        } catch (Exception e) {
            userModelResponse.setResponseMessage("Error while creating user");
        }
        userModelResponse.setId(user.getId());
        userModelResponse.setResponseMessage("customer successfully created");
        return userModelResponse;
    }
}
