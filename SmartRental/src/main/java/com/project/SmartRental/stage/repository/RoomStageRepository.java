package com.project.SmartRental.stage.repository;

import com.project.SmartRental.stage.model.RoomStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomStageRepository extends JpaRepository<RoomStage, Long> {
}
