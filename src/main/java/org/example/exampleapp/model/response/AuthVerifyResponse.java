package org.example.exampleapp.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthVerifyResponse {
    private String token;
    private String username;
    private String errorMessage;
}
