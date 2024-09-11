package org.example.exampleapp.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.UserModelResponse;
import org.example.exampleapp.repository.UserInterface;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreateService {

private final UserInterface userInterface;

    public UserModelResponse createUser(User request){

        UserModelResponse userModelResponse = new UserModelResponse();
        userModelResponse.setName(request.getName());
        userModelResponse.setAmount(request.getAmount());
        userModelResponse.setPhoneNo(request.getPhoneNo());
        userModelResponse.setEmail(request.getEmail());
        userModelResponse.setLastname(request.getLastname());
        userModelResponse.setBankAccountNumber(request.getBankAccountNumber());
        userModelResponse.setIBAN("123131");

        User user = User.builder()
                .IBAN(userModelResponse.getIBAN())
                .name(request.name)
                .bankAccountNumber(request.getBankAccountNumber())
                .amount(request.amount)
                .lastname(request.getLastname())
                .phoneNo(request.getPhoneNo())
                .build();

        userInterface.save(user);
        return userModelResponse;
    }
}
