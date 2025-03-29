package com.surecostproject.takehome.service;

import com.surecostproject.takehome.entity.Drug;
import com.surecostproject.takehome.repository.DrugRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DrugService {
    
    private final DrugRepository drugRepository;

    @Transactional(readOnly = true)
    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Drug getDrugById(UUID id) {
        return drugRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Drug not found with id: " + id));
    }

    @Transactional
    public Drug createDrug(Drug drug) {
        validateDrug(drug);
        return drugRepository.save(drug);
    }

    @Transactional
    public Drug updateDrug(UUID id, Drug drug) {
        validateDrug(drug);
        Drug existingDrug = getDrugById(id);
        
        existingDrug.setName(drug.getName());
        existingDrug.setManufacturerName(drug.getManufacturerName());
        existingDrug.setQuantity(drug.getQuantity());
        existingDrug.setPrice(drug.getPrice());
        
        return drugRepository.save(existingDrug);
    }

    @Transactional
    public void deleteDrug(UUID id) {
        if (!drugRepository.existsById(id)) {
            throw new EntityNotFoundException("Drug not found with id: " + id);
        }
        drugRepository.deleteById(id);
    }

    // Search methods
    @Transactional(readOnly = true)
    public List<Drug> searchByName(String name) {
        return drugRepository.findByNameContainingIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public List<Drug> searchByManufacturer(String manufacturerName) {
        return drugRepository.findByManufacturerNameContainingIgnoreCase(manufacturerName);
    }

    @Transactional(readOnly = true)
    public List<Drug> searchByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return drugRepository.findByPriceBetween(minPrice, maxPrice);
    }

    private void validateDrug(Drug drug) {
        if (drug.getName() == null || drug.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Drug name cannot be empty");
        }
        if (drug.getManufacturerName() == null || drug.getManufacturerName().trim().isEmpty()) {
            throw new IllegalArgumentException("Manufacturer name cannot be empty");
        }
        if (drug.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (drug.getPrice() == null || drug.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }
}
