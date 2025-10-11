package com.project.SmartRental.stage.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractStageReq {

    private String contractStageName;
    private String description;
}
