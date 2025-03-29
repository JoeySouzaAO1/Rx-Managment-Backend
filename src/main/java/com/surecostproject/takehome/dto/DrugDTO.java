package com.surecostproject.takehome.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Schema(description = "Drug data transfer object")
public class DrugDTO {
    
    @Schema(description = "Unique identifier of the drug")
    private UUID uid;
    
    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the drug")
    private String name;
    
    @NotBlank(message = "Manufacturer name is required")
    @Schema(description = "Name of the drug manufacturer")
    private String manufacturerName;
    
    @Min(value = 0, message = "Quantity cannot be negative")
    @Schema(description = "Quantity of the drug in stock")
    private int quantity;
    
    @DecimalMin(value = "0.0", message = "Price must be positive")
    @Schema(description = "Price of the drug")
    private BigDecimal price;
} 