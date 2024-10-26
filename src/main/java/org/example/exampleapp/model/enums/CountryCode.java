package org.example.exampleapp.model.enums;


import lombok.Getter;

@Getter
public enum CountryCode {
    TURKEY_CODE("+90"),
    SPAIN_CODE("+34");


    private final String code;

     CountryCode(String code){
        this.code=code;
    }
}


