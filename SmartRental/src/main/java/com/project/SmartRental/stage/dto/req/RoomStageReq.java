package com.project.SmartRental.stage.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomStageReq {

    private String roomStageName;
    private String description;
}
