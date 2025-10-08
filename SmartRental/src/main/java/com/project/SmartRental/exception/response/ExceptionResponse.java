package com.project.SmartRental.exception.response;

import com.project.SmartRental.exception.custom.AccessDeniedException;
import com.project.SmartRental.exception.custom.ResourceNotFoundException;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String path;
    private String message;
    private Integer code;

    // ✅ Constructor thêm thủ công
    public ExceptionResponse(ResourceNotFoundException ex) {
        this.timestamp = ex.getTimestamp();
        this.path = ex.getPath();
        this.message = ex.getMessage();
        this.code = ex.getCode();
    }

    public ExceptionResponse(AccessDeniedException ex) {
        this.timestamp = ex.getTimestamp();
        this.path = ex.getPath();
        this.message = ex.getMessage();
        this.code = ex.getCode();
    }
}
