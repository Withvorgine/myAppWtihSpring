package org.example.exampleapp.model.enums;


import lombok.Getter;

@Getter
public enum Country {
    TURKEY("TR"),
    SPAIN("ES"),
    GERMANY("DE"),
    UNITED_KINGDOM("GB"),
    ITALY("IT");

    private final String abbreviation;

    Country(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
