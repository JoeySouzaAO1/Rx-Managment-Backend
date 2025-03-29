package com.surecostproject.takehome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(description = "Error response object")
public class ErrorResponse {
    @Schema(description = "HTTP status code")
    private int status;
    
    @Schema(description = "Error message")
    private String message;
    
    @Schema(description = "Timestamp of the error")
    private LocalDateTime timestamp;
    
    @Schema(description = "List of validation errors")
    private List<String> errors;
    
    @Schema(description = "Path where the error occurred")
    private String path;
} 