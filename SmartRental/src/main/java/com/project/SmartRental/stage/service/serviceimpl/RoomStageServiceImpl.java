package com.project.SmartRental.stage.service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.stage.dto.req.RoomStageReq;
import com.project.SmartRental.stage.dto.res.RoomStageResp;
import com.project.SmartRental.stage.model.RoomStage;
import com.project.SmartRental.stage.repository.RoomStageRepository;
import com.project.SmartRental.stage.service.RoomStageService;

@Service
public class RoomStageServiceImpl implements RoomStageService {

    @Autowired
    private RoomStageRepository roomStageRepository;

    @Override
    public Page<RoomStageResp> getAllRoomStage(Pageable pageable) {
        return roomStageRepository.findAll(pageable)
                .map(roomStage -> RoomStageResp.builder()
                .id(roomStage.getId())
                .roomStageName(roomStage.getRoomStageName())
                .description(roomStage.getDescription())
                .isActive(roomStage.isActive())
                .createAt(roomStage.getCreateAt())
                .build()
                );
    }

    @Override
    public RoomStageResp createRoomStage(RoomStageReq roomStageReq) {
        RoomStage roomStage = new RoomStage();

        roomStage.setRoomStageName(roomStageReq.getRoomStageName());
        roomStage.setDescription(roomStageReq.getDescription());

        RoomStage updated = roomStageRepository.save(roomStage);
        return RoomStageResp.builder()
                .id(updated.getId())
                .roomStageName(updated.getRoomStageName())
                .description(updated.getDescription())
                .isActive(updated.isActive())
                .createAt(updated.getCreateAt())
                .build();
    }

    @Override
    public Optional<RoomStageResp> getRoomStageById(Long id) {
        return roomStageRepository.findById(id)
                .map(roomStage -> RoomStageResp.builder()
                .id(roomStage.getId())
                .roomStageName(roomStage.getRoomStageName())
                .isActive(roomStage.isActive())
                .createAt(roomStage.getCreateAt())
                .description(roomStage.getDescription())
                .build()
                );
    }

    @Override
    public RoomStageResp updateRoomStageById(Long id, RoomStageReq roomStageReq) {
        RoomStage roomStage = roomStageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Contract stage not found with id: " + id,
                        "/api/contract-stage/" + id
                )
        );

        // 2️⃣ Kiểm tra giá trị mới, nếu có thay đổi thì mới cập nhật
        if (roomStageReq.getRoomStageName() != null
                && !roomStageReq.getRoomStageName().equals(roomStageReq.getRoomStageName())) {
            roomStage.setRoomStageName(roomStageReq.getRoomStageName());
        }

        if (roomStageReq.getDescription() != null && !roomStageReq.getDescription().equals(roomStage.getDescription())) {
            roomStage.setDescription(roomStageReq.getDescription());
        }

        // 3️⃣ Lưu lại thay đổi
        RoomStage updated = roomStageRepository.save(roomStage);
        return RoomStageResp.builder()
                .id(updated.getId())
                .roomStageName(updated.getRoomStageName())
                .description(updated.getDescription())
                .isActive(updated.isActive())
                .createAt(updated.getCreateAt())
                .build();
    }

    @Override
    public void deleteRoomStageById(Long id) {
        roomStageRepository.deleteById(id);
    }
}
