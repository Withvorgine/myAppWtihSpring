package org.example.exampleapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APP_USER")
public class User {
    @Id
    public String id;

    @Column(name = "name")
    public String name;

    @Column(name = "lastname")
    public String lastname;

    @Column(name = "email")
    public String email;

    @Column(name = "phoneNo")
    public String phoneNo;

    @Column(name = "bankAccountNumber")
    public String bankAccountNumber;

    public void generateBankAccountNumber() {
        this.bankAccountNumber = UUID.randomUUID().toString();
    }

    public void generateId (){
        this.id = UUID.randomUUID().toString();
    }
}
