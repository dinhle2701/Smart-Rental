package com.project.SmartRental.account.controllers;

import com.project.SmartRental.account.dto.AccountReq;
import com.project.SmartRental.account.dto.AccountRes;
import com.project.SmartRental.account.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/account")
@Tag(
        name = "api_account", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến tài khoản"
)
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public ResponseEntity<Page<AccountRes>> getAllAccounts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "accountId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<AccountRes> accountRes = accountService.getAccounts(pageable);

        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountRes>> getAccountById(@PathVariable Long id){
        Optional<AccountRes> accountRes = accountService.getById(id);
        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountRes> updateAccountById(@PathVariable Long id, @RequestBody AccountReq accountReq){
        AccountRes accountRes = accountService.updateAccount(id, accountReq);
        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteAccountById(@PathVariable Long id){
        accountService.deleteAccount(id);
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<Optional<AccountRes>> checkStatus(@PathVariable Long id){
        Optional<AccountRes> accountRes = accountService.checkStatus(id);
        return new ResponseEntity<>(accountRes, HttpStatus.OK);
    }
}
