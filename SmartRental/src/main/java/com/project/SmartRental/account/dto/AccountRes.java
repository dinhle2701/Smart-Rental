package com.project.SmartRental.account.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountRes {

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
