package com.project.SmartRental.stage.service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import com.project.SmartRental.stage.dto.req.ContractStageReq;
import com.project.SmartRental.stage.dto.res.ContractStageResp;
import com.project.SmartRental.stage.model.ContractStage;
import com.project.SmartRental.stage.repository.ContractStageRepository;
import com.project.SmartRental.stage.service.ContractStageService;

@Service
public class ContractStageServiceImpl implements ContractStageService {

    @Autowired
    private ContractStageRepository contractStageRepository;

    // get all
    @Override
    public Page<ContractStageResp> getAllContractStage(Pageable pageable) {
        try {
            return contractStageRepository.findAll(pageable)
                    .map(contractStage -> ContractStageResp.builder()
                    .id(contractStage.getId())
                    .contractStageName(contractStage.getContractStageName())
                    .description(contractStage.getDescription())
                    .isActive(contractStage.isActive())
                    .createAt(contractStage.getCreateAt())
                    .build()
                    );
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage(), e.getLocalizedMessage());
        }
    }

    // create
    @Override
    public ContractStageResp createContractStage(ContractStageReq contractStageReq) {
        ContractStage contractStage = new ContractStage();

        contractStage.setContractStageName(contractStageReq.getContractStageName());
        contractStage.setDescription(contractStageReq.getDescription());

        ContractStage created = contractStageRepository.save(contractStage);
        return ContractStageResp.builder()
                .id(created.getId())
                .contractStageName(created.getContractStageName())
                .description(created.getDescription())
                .isActive(created.isActive())
                .createAt(created.getCreateAt())
                .build();
    }

    // get by id
    @Override
    public Optional<ContractStageResp> getContractStageById(Long id) {
        return contractStageRepository.findById(id)
                .map(contractStage -> ContractStageResp.builder()
                .id(contractStage.getId())
                .contractStageName(contractStage.getContractStageName())
                .isActive(contractStage.isActive())
                .createAt(contractStage.getCreateAt())
                .description(contractStage.getDescription())
                .build()
                );
    }

    // update by id
    @Override
    public ContractStageResp updateContractStageById(Long id, ContractStageReq contractStageReq) {
        ContractStage contractStage = contractStageRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(
                        "Contract stage not found with id: " + id,
                        "/api/contract-stage/" + id
                )
        );

        // 2️⃣ Kiểm tra giá trị mới, nếu có thay đổi thì mới cập nhật
        if (contractStageReq.getContractStageName() != null
                && !contractStageReq.getContractStageName().equals(contractStage.getContractStageName())) {
            contractStage.setContractStageName(contractStageReq.getContractStageName());
        }

        if (contractStageReq.getDescription() != null
                && !contractStageReq.getDescription().equals(contractStage.getDescription())) {
            contractStage.setDescription(contractStageReq.getDescription());
        }

        // 3️⃣ Lưu lại thay đổi
        ContractStage updated = contractStageRepository.save(contractStage);

        return ContractStageResp.builder()
                .id(updated.getId())
                .contractStageName(updated.getContractStageName())
                .description(updated.getDescription())
                .isActive(updated.isActive())
                .createAt(updated.getCreateAt())
                .build();
    }

    // delete by id
    @Override
    public void deleteContractStage(Long id) {
        contractStageRepository.deleteById(id);
    }
}
