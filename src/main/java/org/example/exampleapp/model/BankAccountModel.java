package org.example.exampleapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;
import java.util.Random;


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

    @Column(name = "user_id")
    private String userId;

    @Column(name = "country")
    private String country;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "amount")
    private double amount;


    public void generateIban(String countryCode){

        Random random = new Random();
        int ibanNumbers1 = random.nextInt(100000000, 999999999);
        int ibanNumbers2 = random.nextInt(100000000, 999999999);
        int ibanNumbers3 = random.nextInt(100000, 999999);
        String ibanNumber1String = Integer.toString(ibanNumbers1);
        String ibanNumber2String = Integer.toString(ibanNumbers2);
        String ibanNumber3String = Integer.toString(ibanNumbers3);
        this.iban = countryCode + ibanNumber1String + ibanNumber2String + ibanNumber3String;
    }
}
