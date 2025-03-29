package com.surecostproject.takehome.controller;

import com.surecostproject.takehome.entity.Drug;
import com.surecostproject.takehome.service.DrugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping
    @Operation(summary = "Get all drugs")
    public ResponseEntity<List<Drug>> getAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get drug by ID")
    public ResponseEntity<Drug> getDrugById(@PathVariable UUID id) {
        return ResponseEntity.ok(drugService.getDrugById(id));
    }

    @PostMapping
    @Operation(summary = "Create new drug")
    public ResponseEntity<Drug> createDrug(@RequestBody Drug drug) {
        return new ResponseEntity<>(drugService.createDrug(drug), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing drug")
    public ResponseEntity<Drug> updateDrug(@PathVariable UUID id, @RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.updateDrug(id, drug));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete drug")
    public ResponseEntity<Void> deleteDrug(@PathVariable UUID id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search/name")
    @Operation(summary = "Search drugs by name")
    public ResponseEntity<List<Drug>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(drugService.searchByName(name));
    }

    @GetMapping("/search/manufacturer")
    @Operation(summary = "Search drugs by manufacturer")
    public ResponseEntity<List<Drug>> searchByManufacturer(@RequestParam String manufacturer) {
        return ResponseEntity.ok(drugService.searchByManufacturer(manufacturer));
    }

    @GetMapping("/search/price-range")
    @Operation(summary = "Search drugs by price range")
    public ResponseEntity<List<Drug>> searchByPriceRange(
            @RequestParam BigDecimal minPrice,
            @RequestParam BigDecimal maxPrice) {
        return ResponseEntity.ok(drugService.searchByPriceRange(minPrice, maxPrice));
    }
}
