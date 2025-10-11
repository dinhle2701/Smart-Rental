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
public class RoomStageResp {

    private Long id;
    private String roomStageName;
    private String description;
    private boolean isActive;
    private LocalDateTime createAt;
}
