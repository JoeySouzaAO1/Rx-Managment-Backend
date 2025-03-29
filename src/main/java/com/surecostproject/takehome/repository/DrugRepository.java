package com.surecostproject.takehome.repository;

import com.surecostproject.takehome.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface DrugRepository extends JpaRepository<Drug, UUID> {
    // Custom query methods for search functionality
    List<Drug> findByNameContainingIgnoreCase(String name);
    
    List<Drug> findByManufacturerNameContainingIgnoreCase(String manufacturerName);
    
    List<Drug> findByPriceLessThanEqual(BigDecimal maxPrice);
    
    List<Drug> findByPriceGreaterThanEqual(BigDecimal minPrice);
    
    List<Drug> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    
    List<Drug> findByQuantityGreaterThan(int minQuantity);
} 