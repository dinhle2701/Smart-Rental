package com.project.SmartRental.notification.dto.req;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationReq {

    private Long id;
    private String notificationName;
    private String notificationType;
    private String description;
    private String status;
    private LocalDateTime createAt;
}
