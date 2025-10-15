package com.project.SmartRental.notification.controllers;

import com.project.SmartRental.notification.dto.req.NotificationReq;
import com.project.SmartRental.notification.dto.res.NotificationRes;
import com.project.SmartRental.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
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
@RequestMapping("/api/v1/notification")
@Tag(
        name = "api_notification", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến thông báo"
)
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    // api get all
    @Operation(
            summary = "Get all notification",
            description = "Lấy danh sách toàn bộ thông báo",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "0"))
    )
    @GetMapping("")
    public ResponseEntity<Page<NotificationRes>> getAllNotification(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort sort =  sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<NotificationRes> notificationRes = notificationService.getAllNoti(pageable);
        return new ResponseEntity<>(notificationRes, HttpStatus.OK);
    }

    // api get by id
    @Operation(
            summary = "Get notification by id",
            description = "Lấy thông tin thông báo theo id",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<NotificationRes>> getNotificationById(@PathVariable Long id){
        Optional<NotificationRes> notificationRes = notificationService.getNotificationById(id);
        return new ResponseEntity<>(notificationRes, HttpStatus.OK);
    }

    // api create notification
    @Operation(
            summary = "Create notification",
            description = "Tạo mới thông báo",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
    )
    @PostMapping("")
    public ResponseEntity<NotificationRes> createNotification(@RequestBody NotificationReq notificationReq){
        NotificationRes notificationRes = notificationService.createNotification(notificationReq);
        return new ResponseEntity<>(notificationRes, HttpStatus.CREATED);
    }

    // api update notification
    @Operation(
            summary = "Create notification",
            description = "Cập nhật thông báo theo id",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
    )
    @PutMapping("/{id}")
    public ResponseEntity<NotificationRes> updateNotificationById(@PathVariable Long id, @RequestBody NotificationReq notificationReq){
        NotificationRes notificationRes = notificationService.updateNotification(id, notificationReq);
        return new ResponseEntity<>(notificationRes, HttpStatus.CREATED);
    }

    // api delete notification
    @Operation(
            summary = "Delete notification",
            description = "Xoá thông báo theo id",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        notificationService.deleteNotification(id);
    }

}
