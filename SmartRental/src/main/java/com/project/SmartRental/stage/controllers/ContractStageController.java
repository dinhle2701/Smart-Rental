package com.project.SmartRental.stage.controllers;

import com.project.SmartRental.stage.dto.req.ContractStageReq;
import com.project.SmartRental.stage.dto.res.ContractStageResp;
import com.project.SmartRental.stage.service.ContractStageService;
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
@RequestMapping("/api/v1/contractStage")
@Tag(
        name = "api_stage", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến các giai đoạn của hợp đồng"
)
public class ContractStageController {

    @Autowired
    private ContractStageService contractStageService;

    @GetMapping("")
    public ResponseEntity<Page<ContractStageResp>> getAllContractStage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ContractStageResp> contractStageResps = contractStageService.getAllContractStage(pageable);
        return new ResponseEntity<>(contractStageResps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ContractStageResp>> getContractStageById(@PathVariable Long id) {
        Optional<ContractStageResp> contractStageResp = contractStageService.getContractStageById(id);
        return new ResponseEntity<>(contractStageResp, HttpStatus.OK);
    }

    // api create contract stage
    @PostMapping("")
    public ResponseEntity<ContractStageResp> createContractStage(@RequestBody ContractStageReq contractStageReq) {
        ContractStageResp contractStageResp = contractStageService.createContractStage(contractStageReq);
        return new ResponseEntity<>(contractStageResp, HttpStatus.CREATED);
    }

    // api update by id
    @PutMapping("/{id}")
    public ResponseEntity<ContractStageResp> updateContractStage(@PathVariable Long id, @RequestBody ContractStageReq contractStageReq) {
        ContractStageResp contractStageResp = contractStageService.updateContractStageById(id, contractStageReq);
        return new ResponseEntity<>(contractStageResp, HttpStatus.OK);
    }

    // api delete by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        contractStageService.deleteContractStage(id);
    }

}
