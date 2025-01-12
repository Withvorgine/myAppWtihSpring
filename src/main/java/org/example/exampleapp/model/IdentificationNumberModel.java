package org.example.exampleapp.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@Document(collection = "identity_number")
public class IdentificationNumberModel {
    @Id
    private String id;
    private String identificationNumber;

}
