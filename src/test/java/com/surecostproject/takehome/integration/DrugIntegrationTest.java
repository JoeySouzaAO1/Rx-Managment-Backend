package com.surecostproject.takehome.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.surecostproject.takehome.dto.DrugDTO;
import com.surecostproject.takehome.repository.DrugRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DrugIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        drugRepository.deleteAll();
    }

    @Test
    void createAndRetrieveDrug_Success() throws Exception {
        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setName("Integration Test Drug");
        drugDTO.setManufacturerName("Test Manufacturer");
        drugDTO.setQuantity(100);
        drugDTO.setPrice(new BigDecimal("10.99"));

        // Create drug
        MvcResult createResult = mockMvc.perform(post("/api/drugs")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(drugDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        DrugDTO createdDrug = objectMapper.readValue(
            createResult.getResponse().getContentAsString(), 
            DrugDTO.class
        );

        // Verify retrieval
        mockMvc.perform(get("/api/drugs/" + createdDrug.getUid()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration Test Drug"))
                .andExpect(jsonPath("$.manufacturerName").value("Test Manufacturer"));
    }

    /*
    Additional Integration Tests that could be implemented with more time: */

    @Test
    void testBulkDrugCreation_Success() {
        // Test creating multiple drugs in a single request
        // Verify all drugs are created and can be retrieved
    }

    @Test
    void testSearchDrugs_WithFilters() {
        // Test search functionality with various combinations of:
        // - Name search
        // - Manufacturer search
        // - Price range
        // - Quantity threshold
    }

    @Test
    void testPaginationAndSorting() {
        // Test pagination with different page sizes
        // Test sorting by different fields
        // Verify correct total elements and pages
    }

    @Test
    void testDeleteDrug_WithRelatedData() {
        // Test deletion of drugs
        // Verify proper cleanup of related data
    }

    @Test
    void testErrorScenarios() {
        // Test various error scenarios:
        // - Invalid drug data
        // - Non-existent drug retrieval
        // - Database constraint violations
    }
} 