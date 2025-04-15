package org.example.exampleapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_creditentials")
public class UserCredentials {
    @Id
    private int id;
    private String username;
    private String password;

}
