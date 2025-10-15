package com.project.SmartRental.revenue.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/revenue")
@Tag(
        name = "api_revenue", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến bill"
)
public class RevenueControllers {

}
