package com.project.SmartRental.services.controllers;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.services.dto.req.ServiceRequest;
import com.project.SmartRental.services.dto.res.ServiceResponse;
import com.project.SmartRental.services.model.Services;
import com.project.SmartRental.services.service.ServicesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/service")
@Tag(
        name = "api_service", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến các dịch vụ"
)
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("")
    public ResponseEntity<Page<ServiceResponse>> getServicess(
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

    @PostMapping("")
    public ResponseEntity<ServiceResponse> createService(@RequestBody ServiceRequest serviceRequest) {
        try {
            ServiceResponse serviceResponse = servicesService.saveService(serviceRequest);
            return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ServiceResponse>> getServiceById(@PathVariable Long id){
        try {
            Optional<ServiceResponse> serviceResponse = servicesService.getServiceById(id);
            return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            throw e;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Services> updateService(
            @PathVariable Long id,
            @RequestBody ServiceRequest serviceRequest
    ){
        try {
            Services services = servicesService.updateService(id, serviceRequest);
            return new ResponseEntity<>(services, HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id){
        boolean exists = servicesService.getServiceById(id).isPresent();
        if (!exists) {
            throw new ResourceNotFoundException(
                    "Service not found with id: " + id,
                    "/api/services/" + id
            );
        }

        servicesService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
