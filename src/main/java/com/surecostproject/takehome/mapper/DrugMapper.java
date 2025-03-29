package com.surecostproject.takehome.mapper;

import com.surecostproject.takehome.dto.DrugDTO;
import com.surecostproject.takehome.entity.Drug;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrugMapper {
    
    public Drug toEntity(DrugDTO dto) {
        if (dto == null) return null;
        
        Drug drug = new Drug();
        drug.setUid(dto.getUid());
        drug.setName(dto.getName());
        drug.setManufacturerName(dto.getManufacturerName());
        drug.setQuantity(dto.getQuantity());
        drug.setPrice(dto.getPrice());
        return drug;
    }
    
    public DrugDTO toDTO(Drug entity) {
        if (entity == null) return null;
        
        DrugDTO dto = new DrugDTO();
        dto.setUid(entity.getUid());
        dto.setName(entity.getName());
        dto.setManufacturerName(entity.getManufacturerName());
        dto.setQuantity(entity.getQuantity());
        dto.setPrice(entity.getPrice());
        return dto;
    }
    
    public List<DrugDTO> toDTOList(List<Drug> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
    
    public List<Drug> toEntityList(List<DrugDTO> dtos) {
        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
} 