package com.project.SmartRental.stage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "roomStage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "roomStageName")
    private String roomStageName;

    @Column(name = "description")
    private String description;

    @Column(name = "isActive")
    @Builder.Default
    private boolean isActive = true;

    @Column(name = "createAt")
    @Builder.Default
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "updateAt")
    @Builder.Default
    private LocalDateTime updateAt = LocalDateTime.now();
}

//public enum RoomStage {
//    AVAILABLE, // có sẵn
//    BOOKED, // đã đặt cọc
//    OCCUPIED, // đang thuê
//    MAINTENANCE, // bảo trì
//    INACTIVE // ngưng hoạt động
//}
