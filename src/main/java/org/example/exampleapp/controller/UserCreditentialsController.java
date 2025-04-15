package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.UserCredentials;
import org.example.exampleapp.model.response.AuthVerifyResponse;
import org.example.exampleapp.service.UserDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("userCredentials")
@RequiredArgsConstructor
public class UserCreditentialsController {

    private final UserDetailService userDetailService;

    @PostMapping("/register")
    public UserCredentials saveUser(@RequestBody UserCredentials userCredentials) {
        userDetailService.saveUser(userCredentials);
        return userCredentials;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserCredentials userCredentials) {
        try {
            return userDetailService.verify(userCredentials);
        }catch (Exception e) {
            return e.getMessage();
        }
    }
}
