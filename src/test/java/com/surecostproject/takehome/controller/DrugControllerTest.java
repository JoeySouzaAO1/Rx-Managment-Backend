package com.surecostproject.takehome.controller;

import com.surecostproject.takehome.dto.DrugDTO;
import com.surecostproject.takehome.entity.Drug;
import com.surecostproject.takehome.mapper.DrugMapper;
import com.surecostproject.takehome.service.DrugService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DrugControllerTest {

    @Mock
    private DrugService drugService;

    @Mock
    private DrugMapper drugMapper;

    @InjectMocks
    private DrugController drugController;

    private Drug testDrug;
    private DrugDTO testDrugDTO;
    private UUID testId;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        testDrug = new Drug();
        testDrug.setUid(testId);
        testDrug.setName("Test Drug");
        testDrug.setManufacturerName("Test Manufacturer");
        testDrug.setQuantity(100);
        testDrug.setPrice(new BigDecimal("10.99"));

        testDrugDTO = new DrugDTO();
        testDrugDTO.setUid(testId);
        testDrugDTO.setName("Test Drug");
        testDrugDTO.setManufacturerName("Test Manufacturer");
        testDrugDTO.setQuantity(100);
        testDrugDTO.setPrice(new BigDecimal("10.99"));
    }

    @Test
    void createDrug_Success() {
        // Arrange
        when(drugMapper.toEntity(testDrugDTO)).thenReturn(testDrug);
        when(drugService.createDrug(testDrug)).thenReturn(testDrug);
        when(drugMapper.toDTO(testDrug)).thenReturn(testDrugDTO);

        // Act
        ResponseEntity<DrugDTO> response = drugController.createDrug(testDrugDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(testDrugDTO, response.getBody());
        verify(drugService).createDrug(testDrug);
        verify(drugMapper).toEntity(testDrugDTO);
        verify(drugMapper).toDTO(testDrug);
    }

    @Test
    void getAllDrugs_Success() {
        // Arrange
        List<Drug> drugs = Arrays.asList(testDrug);
        Page<Drug> drugPage = new PageImpl<>(drugs);
        Pageable pageable = PageRequest.of(0, 20);

        when(drugService.getAllDrugs(any(Pageable.class))).thenReturn(drugPage);
        when(drugMapper.toDTO(testDrug)).thenReturn(testDrugDTO);

        // Act
        ResponseEntity<Page<DrugDTO>> response = drugController.getAllDrugs(0, 20, "name", "asc");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getTotalElements());
        verify(drugService).getAllDrugs(any(Pageable.class));
        verify(drugMapper).toDTO(testDrug);
    }

    @Test
    void deleteDrug_Success() {
        // Arrange
        doNothing().when(drugService).deleteDrug(testId);

        // Act
        ResponseEntity<Void> response = drugController.deleteDrug(testId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(drugService).deleteDrug(testId);
    }

    @Test
    void updateDrug_Success() {
        // Arrange
        when(drugMapper.toEntity(testDrugDTO)).thenReturn(testDrug);
        when(drugService.updateDrug(eq(testId), any(Drug.class))).thenReturn(testDrug);
        when(drugMapper.toDTO(testDrug)).thenReturn(testDrugDTO);

        // Act
        ResponseEntity<DrugDTO> response = drugController.updateDrug(testId, testDrugDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testDrugDTO, response.getBody());
        verify(drugService).updateDrug(eq(testId), any(Drug.class));
        verify(drugMapper).toEntity(testDrugDTO);
        verify(drugMapper).toDTO(testDrug);
    }
} 