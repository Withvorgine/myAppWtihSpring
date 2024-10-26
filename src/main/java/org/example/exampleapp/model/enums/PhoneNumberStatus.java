package org.example.exampleapp.model.enums;

import lombok.Getter;

@Getter
public enum PhoneNumberStatus {
    AVAILABLE("Available"),
    TAKEN("Taken");

    private final String phoneNumberStatus;

    PhoneNumberStatus(String phoneNumberStatus) {
        this.phoneNumberStatus = phoneNumberStatus;
    }
}
