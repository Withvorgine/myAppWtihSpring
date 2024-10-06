package org.example.exampleapp.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModelResponse {
    private String id;
    private String responseMessage;

}