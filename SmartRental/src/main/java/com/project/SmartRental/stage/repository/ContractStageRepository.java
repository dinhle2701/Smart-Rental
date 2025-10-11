package com.project.SmartRental.stage.repository;

import com.project.SmartRental.stage.model.ContractStage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractStageRepository extends JpaRepository<ContractStage, Long> {
}
