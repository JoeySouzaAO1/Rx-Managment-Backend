package com.surecostproject.takehome.controller;

import com.surecostproject.takehome.dto.BulkDrugDTO;
import com.surecostproject.takehome.dto.DrugDTO;
import com.surecostproject.takehome.entity.Drug;
import com.surecostproject.takehome.mapper.DrugMapper;
import com.surecostproject.takehome.service.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/drugs")
@RequiredArgsConstructor
@Tag(name = "Drug Management", description = "APIs for managing drug inventory")
public class DrugController {

    private final DrugService drugService;
    private final DrugMapper drugMapper;

    @GetMapping
    @Operation(summary = "Get all drugs")
    public ResponseEntity<List<DrugDTO>> getAllDrugs() {
        List<Drug> drugs = drugService.getAllDrugs();
        return ResponseEntity.ok(drugMapper.toDTOList(drugs));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get drug by ID")
    public ResponseEntity<DrugDTO> getDrugById(@PathVariable UUID id) {
        Drug drug = drugService.getDrugById(id);
        return ResponseEntity.ok(drugMapper.toDTO(drug));
    }

    @PostMapping
    @Operation(summary = "Create new drug")
    public ResponseEntity<DrugDTO> createDrug(@Valid @RequestBody DrugDTO drugDTO) {
        Drug drug = drugMapper.toEntity(drugDTO);
        Drug createdDrug = drugService.createDrug(drug);
        return new ResponseEntity<>(drugMapper.toDTO(createdDrug), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    @Operation(summary = "Create multiple drugs")
    public ResponseEntity<List<DrugDTO>> createBulkDrugs(@Valid @RequestBody BulkDrugDTO bulkDrugDTO) {
        List<Drug> drugs = drugMapper.toEntityList(bulkDrugDTO.getDrugs());
        List<Drug> createdDrugs = drugService.createBulkDrugs(drugs);
        return new ResponseEntity<>(drugMapper.toDTOList(createdDrugs), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing drug")
    public ResponseEntity<DrugDTO> updateDrug(@PathVariable UUID id, @Valid @RequestBody DrugDTO drugDTO) {
        Drug drug = drugMapper.toEntity(drugDTO);
        Drug updatedDrug = drugService.updateDrug(id, drug);
        return ResponseEntity.ok(drugMapper.toDTO(updatedDrug));
    }

    @PutMapping("/bulk")
    @Operation(summary = "Update multiple drugs")
    public ResponseEntity<List<DrugDTO>> updateBulkDrugs(@Valid @RequestBody BulkDrugDTO bulkDrugDTO) {
        List<Drug> drugs = drugMapper.toEntityList(bulkDrugDTO.getDrugs());
        List<Drug> updatedDrugs = drugService.updateBulkDrugs(drugs);
        return ResponseEntity.ok(drugMapper.toDTOList(updatedDrugs));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete drug")
    public ResponseEntity<Void> deleteDrug(@PathVariable UUID id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bulk")
    @Operation(summary = "Delete multiple drugs")
    public ResponseEntity<Void> deleteBulkDrugs(@RequestBody List<UUID> ids) {
        drugService.deleteBulkDrugs(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    @Operation(summary = "Search drugs by name")
    public ResponseEntity<List<DrugDTO>> searchByName(@RequestParam String name) {
        List<Drug> drugs = drugService.searchByName(name);
        return ResponseEntity.ok(drugMapper.toDTOList(drugs));
    }

    @GetMapping("/search/manufacturer")
    @Operation(summary = "Search drugs by manufacturer")
    public ResponseEntity<List<DrugDTO>> searchByManufacturer(@RequestParam String manufacturer) {
        List<Drug> drugs = drugService.searchByManufacturer(manufacturer);
        return ResponseEntity.ok(drugMapper.toDTOList(drugs));
    }

    @GetMapping("/search/price-range")
    @Operation(summary = "Search drugs by price range")
    public ResponseEntity<List<DrugDTO>> searchByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        List<Drug> drugs = drugService.searchByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(drugMapper.toDTOList(drugs));
    }
} 