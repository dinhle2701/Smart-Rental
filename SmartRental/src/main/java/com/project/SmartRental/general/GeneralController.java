package com.project.SmartRental.general;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.general.services.dto.req.ServiceRequest;
import com.project.SmartRental.general.services.dto.res.ServiceResponse;
import com.project.SmartRental.general.services.service.ServicesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/general")
@Tag(
        name = "api_home", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến cài đặt chung"
)
public class GeneralController {

    @Autowired
    private ServicesService servicesService;

    // ============================SERVICE================================
    @Operation(
            summary = "Get all vehicles",
            description = "Lấy danh sách toàn bộ dịch vụ"
    )
    @GetMapping("/services")
    public ResponseEntity<Page<ServiceResponse>> getAllServices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        try {
            Sort sort = sortDir.equalsIgnoreCase("asc")
                    ? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<ServiceResponse> vehicles = servicesService.getAllService(pageable);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    @Operation(
            summary = "Create service",
            description = "Tạo mới dịch vụ"
    )
    @PostMapping("/services")
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceResponse serviceResponse = servicesService.saveService(serviceRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("");
        }
    }

    @Operation(
            summary = "Get service by id",
            description = "Lấy thông tin dịch vụ theo id"
    )
    @GetMapping("/services/{id}")
    public ResponseEntity<Optional<ServiceResponse>> getServiceById(@PathVariable Long id) {
        try {
            Optional<ServiceResponse> serviceResponse = servicesService.getServiceById(id);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }

    @Operation(
            summary = "Update service by id",
            description = "Cập nhật dịch vụ theo id"
    )
    @PutMapping("/services/{id}")
    public ResponseEntity<ServiceResponse> updateService(
            @PathVariable Long id,
            @RequestBody ServiceRequest serviceRequest
    ) {
        try {
            ServiceResponse services = servicesService.updateService(id, serviceRequest);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw e;
        }
    }

    @Operation(
            summary = "Delete service by id",
            description = "Xoá dịch vụ theo id"
    )
    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        boolean exists = servicesService.getServiceById(id).isPresent();
        if (!exists) {
            throw new ResourceNotFoundException(
                    "Service not found with id: " + id,
                    "/api/services/" + id);
        }

        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
    // ============================================================

    // ===========================ROLE=================================
    // ============================================================
}
