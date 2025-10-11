package com.project.SmartRental.stage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "contractStage")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContractStage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "contractStageName")
    private String contractStageName;

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
//public enum ContractStage {
//    DRAFT, // bản nháp
//    PENDING, // chờ xác nhận
//    ACTIVE, // hiệu lực
//    EXPIRED, // hết hạn
//    TERMINATED, // chấm dứt sớm
//    COMPLETED, // hoàn tất
//    CANCELLED // huỷ bỏ
//}
