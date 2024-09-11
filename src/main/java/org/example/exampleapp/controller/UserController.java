package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;


import org.example.exampleapp.model.User;
import org.example.exampleapp.model.UserModelRequest;
import org.example.exampleapp.model.UserModelResponse;
import org.example.exampleapp.service.UserCreateService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserCreateService userCreateService;

    @PostMapping("/api/createUser")
    public ResponseEntity<UserModelResponse> createUser(@RequestBody User request) {
        UserModelResponse response = userCreateService.createUser(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
