package com.project.SmartRental.account.service;

import com.project.SmartRental.account.dto.AccountReq;
import com.project.SmartRental.account.dto.AccountRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService {

    Page<AccountRes> getAccounts(Pageable pageable);

    Optional<AccountRes> getById(Long id);

    AccountRes updateAccount(Long id, AccountReq accountReq);

    void deleteAccount(Long id);

    Optional<AccountRes> checkStatus(Long id);
}
