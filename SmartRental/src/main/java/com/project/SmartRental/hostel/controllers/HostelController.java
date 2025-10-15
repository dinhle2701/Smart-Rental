package com.project.SmartRental.hostel.controllers;

import com.project.SmartRental.hostel.dto.HostelReq;
import com.project.SmartRental.hostel.dto.HostelRes;
import com.project.SmartRental.hostel.service.HostelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hostel")
@Tag(
        name = "api_hostel", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến nhà trọ"
)
public class HostelController {
    @Autowired
    private HostelService hostelService;

    @Operation(
            summary = "Get all hostel",
            description = "Lấy danh sách toàn bộ nhà trọ",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "0"))
    )
    @GetMapping("")
    public ResponseEntity<Page<HostelRes>> getAllHostel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HostelRes> hostelRes = hostelService.getHostels(pageable);
        return new ResponseEntity<>(hostelRes, HttpStatus.OK);
    }

    @Operation(
            summary = "Get hostel by id",
            description = "Lấy thông tin nhà trọ theo id",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<Optional<HostelRes>> getHostelById(@PathVariable Long id){
        Optional<HostelRes> hostelRes = hostelService.getById(id);
        return new ResponseEntity<>(hostelRes, HttpStatus.OK);
    }

    // api create hostel
    @Operation(
            summary = "Create hostel",
            description = "Tạo mới nhà trọ",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
    )
    @PostMapping("")
    public ResponseEntity<HostelRes> createNotification(@RequestBody HostelReq hostelReq){
        HostelRes hostelRes = hostelService.createHostel(hostelReq);
        return new ResponseEntity<>(hostelRes, HttpStatus.CREATED);
    }

    // api update hostel
    @Operation(
            summary = "Update hostel",
            description = "Cập nhật nhà trọ",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
    )
    @PutMapping("/{id}")
    public ResponseEntity<HostelRes> updateHostelById(@PathVariable Long id, @RequestBody HostelReq hostelReq){
        HostelRes hostelRes = hostelService.updatedHostel(id, hostelReq);
        return new ResponseEntity<>(hostelRes, HttpStatus.OK);
    }

    // api update partial hostel
    @Operation(
            summary = "Update partial hostel",
            description = "Cập nhật một phần nhà trọ",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
    )
    @PatchMapping("/{id}")
    public ResponseEntity<HostelRes> updatePartialHostelById(@PathVariable Long id, @RequestBody String field, @RequestBody HostelReq hostelReq){
        HostelRes hostelRes = hostelService.updatedPartialHostel(id, field, hostelReq);
        return new ResponseEntity<>(hostelRes, HttpStatus.OK);
    }

    // api delete hostel
    @Operation(
            summary = "Delete hostel by id",
            description = "Xoá nhà trọ theo id",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "5"))
    )
    @DeleteMapping("/{id}")
    public void deleteHostelById(@PathVariable Long id){
        hostelService.deleteHostel(id);
    }

}
