package com.surecostproject.takehome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Bulk drug operations data transfer object")
public class BulkDrugDTO {
    
    @NotEmpty(message = "Drug list cannot be empty")
    @Valid
    @Schema(description = "List of drugs for bulk operation")
    private List<DrugDTO> drugs;
} 