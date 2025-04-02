package org.example.exampleapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.exampleapp.model.User;
import org.example.exampleapp.model.request.UserModelRequest;
import org.example.exampleapp.model.response.UserModelResponse;
import org.example.exampleapp.repository.UserRepository;
import org.example.exampleapp.service.CustomerCreateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerCreateService customerCreateService;
    private final UserRepository userRepository;

    @PostMapping("/api/createUser")
    public ResponseEntity<UserModelResponse> createUser(@RequestBody UserModelRequest request) {
        try {
            UserModelResponse response = customerCreateService.createUser(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @GetMapping(value = "{id}", produces = {"application/json;charset=utf-8"})
    public ResponseEntity<User> getUser(@PathVariable("id") String id)  {
        return new ResponseEntity<>(userRepository.findUserById(id), HttpStatus.OK);
    }
}
