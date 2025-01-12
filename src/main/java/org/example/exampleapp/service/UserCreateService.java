package org.example.exampleapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.request.UserModelRequest;
import org.example.exampleapp.model.response.UserModelResponse;
import org.example.exampleapp.repository.UserRepository;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.model.*;
import software.amazon.awssdk.services.ses.SesClient;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCreateService {

    private final UserRepository userRepository;
    private final BankAccountService bankAccountService;
    private final PhoneNumberService phoneNumberService;
    private final EmailService emailService;
    private final IdentificationNumberService identificationNumberService;

    public UserModelResponse createUser(UserModelRequest request) {

        User user = User.builder()
                .name(request.getName())
                .bankAccountNumber(request.getBankAccountNumber())
                .email(request.getEmail())
                .lastname(request.getLastname())
                .phoneNo(request.getPhoneNo())
                .country(request.getCountry())
                .createdDate(new Date())
                .identificationNumber(request.getIdentificationNumber())
                .build();


        Boolean isIdentificationNumberExist = identificationNumberService.validateIdentificationNumber(request.getIdentificationNumber());
        if (!isIdentificationNumberExist) {
            throw new RuntimeException("Invalid identification number");
        }

        boolean phoneNumberValidate = phoneNumberService.validatePhoneNumberIfExistInDB(request.getPhoneNo());
        boolean isPhoneNumberAvailable = phoneNumberService.validatePhoneNumberIfAvailable(request.getPhoneNo());
        if (!phoneNumberValidate || !isPhoneNumberAvailable) {
            throw new RuntimeException("Invalid phone number");
        }
        phoneNumberService.changeStatusToTaken(request.getPhoneNo());

        UserModelResponse userModelResponse = new UserModelResponse();
        try {
            user.generateBankAccountNumber();
            user.generateId();
            //Customer creation
            userRepository.save(user);
            log.info("User created: {}", user.getId());
        } catch (Exception e) {
            userModelResponse.setResponseMessage("Error while creating user");
        }

        generateBankAccount(user, userModelResponse);

        userModelResponse.setId(user.getId());
        userModelResponse.setResponseMessage("customer successfully created");

        SendEmailRequest sendEmailRequest = emailService.getSendEmailRequest(user);

        SesClient sesClientBuild = SesClient.builder().region(Region.EU_CENTRAL_1).build();
        sesClientBuild.sendEmail(sendEmailRequest);

        return userModelResponse;
    }

    private void generateBankAccount(User user, UserModelResponse userModelResponse) {
        try {
            //Posting bankAccountService to create bank account
            bankAccountService.createBankAccount(user.getBankAccountNumber(), user.getId(), user.getCountry());
        } catch (Exception e) {
            userModelResponse.setResponseMessage("Bank account creation failed");
        }
    }

}
