package com.project.SmartRental.account.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
@Tag(
        name = "api_account", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến tài khoản"
)
public class AccountController {

}
