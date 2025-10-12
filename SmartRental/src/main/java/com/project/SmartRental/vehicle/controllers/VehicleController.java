package com.project.SmartRental.vehicle.controllers;

import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.vehicle.dto.VehicleMapper;
import com.project.SmartRental.vehicle.dto.req.VehicleRequest;
import com.project.SmartRental.vehicle.dto.res.VehicleResponse;
import com.project.SmartRental.vehicle.model.Vehicle;
import com.project.SmartRental.vehicle.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(
        name = "api_vehicle", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến phương tiện"
)
@RestController
@RequestMapping("/api/v1/vehicle")
//@Slf4j
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private VehicleMapper vehicleMapper;
    private static final Logger log = LoggerFactory.getLogger(VehicleController.class);

    // get all
    @Operation(
            summary = "Get all vehicles",
            description = "Lấy danh sách toàn bộ phương tiện",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "0"))
    )
    @GetMapping("")
    public ResponseEntity<Page<Vehicle>> getVehicles(
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
            Page<Vehicle> vehicles = vehicleService.getAllVehicle(pageable);
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // get by id
    @Operation(
            summary = "Get vehicle by ID",
            description = "Lấy thông tin chi tiết phương tiện theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "1"))
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> getVehicleById(@PathVariable Long id, HttpServletRequest request) {
        try {
            Optional<Vehicle> vehicle = vehicleService.getVehicleById(id);

            if (vehicle.isPresent()) {
                log.info("✅ Found vehicle with id: {}", id);
                return ResponseEntity.ok(vehicle.get());
            } else {
                // 🔹 Ném lỗi có path để ExceptionResponse tự nhận
                throw new ResourceNotFoundException("Không tìm thấy phương tiện với id: " + id, request.getRequestURI());
            }

        } catch (ResourceNotFoundException e) {
            // ❗ Không cần tạo ExceptionResponse thủ công nữa
            // Chỉ cần ném lại exception — GlobalExceptionHandler sẽ lo
            throw e;
        }
    }

    // post
    @Operation(
            summary = "Create new vehicle",
            description = "Tạo mới phương tiện ",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "2"))
    )
    @PostMapping("")
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody VehicleRequest request) {
        try {
            Vehicle vehicle = vehicleMapper.toEntity(request);
            Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
            VehicleResponse response = vehicleMapper.toResponse(savedVehicle);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResourceNotFoundException e) {
            log.warn("Vehicle resource not found: {}", e.getMessage());
            throw e; // ✅ ném lại chính exception cũ
        } catch (IllegalArgumentException e) {
            log.error("Invalid request: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Unexpected error while creating vehicle", e);
            // Nếu muốn ném về 1 lỗi chung
            throw new DataIntegrityViolationException("");
        }
    }

    // put
    @Operation(
            summary = "Update vehicle",
            description = "Cập nhật toàn bộ thông tin phương tiện theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "3"))
    )
    @PutMapping("/{id}")
    public String updateVehicle(@PathVariable UUID id) {
        return "Update vehicle with ID: " + id;
    }

    // patch
    @Operation(
            summary = "Update partial vehicle",
            description = "Cập nhật một phần thông tin phương tiện theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "4"))
    )
    @PatchMapping("/{id}")
    public String partiallyUpdateVehicle(@PathVariable Long id) {
        return "Partially update tenant with ID: " + id;
    }

    @Operation(
            summary = "Delete vehicle",
            description = "Xóa phương tiện theo ID",
            extensions = @Extension(properties = @ExtensionProperty(name = "x-order", value = "5"))
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        vehicleService.deleteVehicleById(id);
    }
}
