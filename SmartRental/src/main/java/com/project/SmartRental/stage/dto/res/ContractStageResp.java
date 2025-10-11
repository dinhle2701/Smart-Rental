package com.project.SmartRental.stage.dto.res;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractStageResp {

    private Long id;
    private String contractStageName;
    private String description;
    private boolean isActive;
    private LocalDateTime createAt;
}
