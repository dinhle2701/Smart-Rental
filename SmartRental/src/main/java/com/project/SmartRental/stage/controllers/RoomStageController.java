package com.project.SmartRental.stage.controllers;

import com.project.SmartRental.stage.dto.req.ContractStageReq;
import com.project.SmartRental.stage.dto.req.RoomStageReq;
import com.project.SmartRental.stage.dto.res.ContractStageResp;
import com.project.SmartRental.stage.dto.res.RoomStageResp;
import com.project.SmartRental.stage.service.ContractStageService;
import com.project.SmartRental.stage.service.RoomStageService;
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
@RequestMapping("/api/v1/roomStage")
@Tag(
        name = "api_stage", // 👈 tên bạn muốn hiển thị
        description = "API xử lý các nghiệp vụ liên quan đến các giai đoạn của phòng"
)
public class RoomStageController {
    @Autowired
    private RoomStageService roomStageService;

    @GetMapping("")
    public ResponseEntity<Page<RoomStageResp>> getAllContractStage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir
    ){
        Sort sort = sortDir.equalsIgnoreCase("asc")
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<RoomStageResp> roomStageResps = roomStageService.getAllRoomStage(pageable);
        return new ResponseEntity<>(roomStageResps, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<RoomStageResp>> getContractStageById(@PathVariable Long id){
        Optional<RoomStageResp> contractStageResp = roomStageService.getRoomStageById(id);
        return new ResponseEntity<>(contractStageResp, HttpStatus.OK);
    }

    // api create contract stage
    @PostMapping("")
    public ResponseEntity<RoomStageResp> createContractStage(@RequestBody RoomStageReq roomStageReq){
        RoomStageResp roomStageResp = roomStageService.createRoomStage(roomStageReq);
        return new ResponseEntity<>(roomStageResp, HttpStatus.CREATED);
    }

    // api update by id
    @PutMapping("/{id}")
    public ResponseEntity<RoomStageResp> updateContractStage(@PathVariable Long id, @RequestBody RoomStageReq roomStageReq){
        RoomStageResp roomStageResp = roomStageService.updateRoomStageById(id, roomStageReq);
        return new ResponseEntity<>(roomStageResp, HttpStatus.OK);
    }

    // api delete by id
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        roomStageService.deleteRoomStageById(id);
    }

}
