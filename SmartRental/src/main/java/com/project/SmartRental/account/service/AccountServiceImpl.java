package com.project.SmartRental.account.service;

import com.project.SmartRental.account.dto.AccountReq;
import com.project.SmartRental.account.dto.AccountRes;
import com.project.SmartRental.account.model.Account;
import com.project.SmartRental.account.repository.AccountRepository;
import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<AccountRes> getAccounts(Pageable pageable) {
        return accountRepository.findAll(pageable)
                .map(account -> AccountRes.builder()
                        .accountId(account.getAccountId())
                        .accountName(account.getAccountName())
                        .accountCode(account.getAccountCode())
                        .phoneNumber(account.getPhoneNumber())
                        .address(account.getAddress())
                        .imgURL(account.getImgURL())
                        .email(account.getEmail())
                        .role(account.getRole())
                        .createAt(account.getCreateAt())
                        .updateAt(account.getUpdateAt())
                        .build());
    }

    @Override
    public Optional<AccountRes> getById(Long id) {
        return accountRepository.findById(id)
                .map(account -> AccountRes.builder()
                        .accountId(account.getAccountId())
                        .accountName(account.getAccountName())
                        .accountCode(account.getAccountCode())
                        .phoneNumber(account.getPhoneNumber())
                        .address(account.getAddress())
                        .imgURL(account.getImgURL())
                        .email(account.getEmail())
                        .role(account.getRole())
                        .createAt(account.getCreateAt())
                        .updateAt(account.getUpdateAt())
                        .build());
    }

    @Override
    public AccountRes updateAccount(Long id, AccountReq accountReq) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Account not found with id: " + id,
                        "/api/v1/account/" + id
                )
        );

        // ✅ kiểm tra và cập nhật từng field nếu có thay đổi
        if (accountReq.getAccountName() != null
                && !accountReq.getAccountName().equals(account.getAccountName())) {
            account.setAccountName(accountReq.getAccountName());
        }

        if (accountReq.getAddress() != null
                && !accountReq.getAddress().equals(account.getAddress())) {
            account.setAddress(accountReq.getAddress());
        }

        if (accountReq.getImgURL() != null
                && !accountReq.getImgURL().equals(account.getImgURL())) {
            account.setImgURL(accountReq.getImgURL());
        }

        if (accountReq.getPassword() != null
                && !passwordEncoder.matches(accountReq.getPassword(), account.getPassword())) {
            account.setPassword(passwordEncoder.encode(accountReq.getPassword()));
        }

        // cập nhật thời gian
        account.setUpdateAt(LocalDateTime.now());

        Account updated = accountRepository.save(account);

        return AccountRes.builder()
                .accountId(updated.getAccountId())
                .accountName(updated.getAccountName())
                .accountCode(updated.getAccountCode())
                .address(updated.getAddress())
                .phoneNumber(updated.getPhoneNumber())
                .imgURL(updated.getImgURL())
                .email(updated.getEmail())
                .role(updated.getRole())
                .createAt(updated.getCreateAt())
                .updateAt(updated.getUpdateAt())
                .build();
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Optional<AccountRes> checkStatus(Long id) {
        return accountRepository.findById(id)
                .map(account -> AccountRes.builder()
                        .accountId(account.getAccountId())
                        .accountName(account.getAccountName())
                        .accountCode(account.getAccountCode())
                        .phoneNumber(account.getPhoneNumber())
                        .address(account.getAddress())
                        .imgURL(account.getImgURL())
                        .email(account.getEmail())
                        .role(account.getRole())
                        .createAt(account.getCreateAt())
                        .updateAt(account.getUpdateAt())
                        .build());
    }
}
