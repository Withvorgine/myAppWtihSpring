package org.example.exampleapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BANK_ACCOUNT")
public class BankAccountModel {
    @Id
    private String id;

    @Column(name = "iban")
    private String iban;



}
