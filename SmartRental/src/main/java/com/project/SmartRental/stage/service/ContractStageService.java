package com.project.SmartRental.stage.service;

import com.project.SmartRental.stage.dto.req.ContractStageReq;
import com.project.SmartRental.stage.dto.res.ContractStageResp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ContractStageService {

    Page<ContractStageResp> getAllContractStage(Pageable pageable);

    ContractStageResp createContractStage(ContractStageReq contractStageReq);

    Optional<ContractStageResp> getContractStageById(Long id);

    ContractStageResp updateContractStageById(Long id, ContractStageReq contractStageReq);

    void deleteContractStage(Long id);
}
