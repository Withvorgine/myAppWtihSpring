package org.example.exampleapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PHONE_NUMBER")
public class PhoneNumberModel {
    @Id
    String Id;

    @Column(name = "country_code")
    String countryCode;

    @Column(name = "phoneno")
    String phoneNumber;

    @Column(name = "country")
    String countryName;

    @Column(name = "phone_number_status")
    String phoneNumberStatus;

    @Column(name = "created_date")
    Date createdDate;
}
