package org.example.exampleapp.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Data
@Document(collection = "identity")
public class IdentityCard {
    @Id
    private String id;
    private String identificationNumber;
    private String name;
    private String Lastname;
    private String Gender;
    private Date createdDate;
    private Date modifiedDate;

}
