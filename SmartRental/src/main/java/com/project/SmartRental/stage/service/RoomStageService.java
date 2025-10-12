package com.project.SmartRental.stage.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.stage.dto.req.RoomStageReq;
import com.project.SmartRental.stage.dto.res.RoomStageResp;

@Service
public interface RoomStageService {

    Page<RoomStageResp> getAllRoomStage(Pageable pageable);

    RoomStageResp createRoomStage(RoomStageReq roomStageReq);

    Optional<RoomStageResp> getRoomStageById(Long id);

    RoomStageResp updateRoomStageById(Long id, RoomStageReq roomStageReq);

    void deleteRoomStageById(Long id);
}
