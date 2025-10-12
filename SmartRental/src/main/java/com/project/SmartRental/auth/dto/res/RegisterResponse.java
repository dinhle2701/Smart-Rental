package com.project.SmartRental.auth.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse {
        private Long accountId;
        private String email;
        private String role;
        private String accountCode;
}
