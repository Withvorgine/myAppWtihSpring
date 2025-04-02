package org.example.exampleapp.model.response;

import lombok.Data;

@Data
public class ErrorModel {
    private String errorMessage;
    private Integer statusCode;
    private String path;
}
