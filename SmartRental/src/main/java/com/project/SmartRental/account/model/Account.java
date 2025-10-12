package com.project.SmartRental.account.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountCode;

    private String accountName;

    private String email;

    private String password;

    private String phoneNumber;

    private String address;

    private String role;

    private String imgURL;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
