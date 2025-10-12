package com.project.SmartRental.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountReq {

    private String accountCode;
    private String accountName;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    private String imgURL;
}
