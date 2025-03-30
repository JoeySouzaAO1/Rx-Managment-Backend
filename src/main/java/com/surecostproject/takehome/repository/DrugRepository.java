package com.surecostproject.takehome.repository;

import com.surecostproject.takehome.entity.Drug;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.UUID;

@Repository
public interface DrugRepository extends JpaRepository<Drug, UUID> {
    // Custom query methods for search functionality with pagination
    Page<Drug> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
    Page<Drug> findByManufacturerNameContainingIgnoreCase(String manufacturerName, Pageable pageable);
    
    Page<Drug> findByPriceLessThanEqual(BigDecimal maxPrice, Pageable pageable);
    
    Page<Drug> findByPriceGreaterThanEqual(BigDecimal minPrice, Pageable pageable);
    
    Page<Drug> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
    
    Page<Drug> findByQuantityGreaterThan(int minQuantity, Pageable pageable);
} 