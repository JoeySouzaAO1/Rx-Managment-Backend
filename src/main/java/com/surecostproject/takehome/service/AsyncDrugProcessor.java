package com.surecostproject.takehome.service;

import com.surecostproject.takehome.entity.Drug;
import com.surecostproject.takehome.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AsyncDrugProcessor {
    private static final int CHUNK_SIZE = 50;
    
    private final DrugRepository drugRepository;
    
    @Async("drugProcessingExecutor")
    @Transactional
    public List<Drug> processBulkCreation(List<Drug> drugs) {
        List<Drug> processedDrugs = new ArrayList<>();
        
        // Process in chunks
        for (int i = 0; i < drugs.size(); i += CHUNK_SIZE) {
            int endIndex = Math.min(i + CHUNK_SIZE, drugs.size());
            List<Drug> chunk = drugs.subList(i, endIndex);
            List<Drug> savedChunk = drugRepository.saveAll(chunk);
            processedDrugs.addAll(savedChunk);
        }
        
        return processedDrugs;
    }
    
    @Async("drugProcessingExecutor")
    @Transactional
    public List<Drug> processBulkUpdate(List<Drug> drugs) {
        List<Drug> processedDrugs = new ArrayList<>();
        
        for (int i = 0; i < drugs.size(); i += CHUNK_SIZE) {
            int endIndex = Math.min(i + CHUNK_SIZE, drugs.size());
            List<Drug> chunk = drugs.subList(i, endIndex);
            List<Drug> updatedChunk = drugRepository.saveAll(chunk);
            processedDrugs.addAll(updatedChunk);
        }
        
        return processedDrugs;
    }
    
    @Async("drugProcessingExecutor")
    @Transactional
    public void processBulkDeletion(List<UUID> ids) {
        for (int i = 0; i < ids.size(); i += CHUNK_SIZE) {
            int endIndex = Math.min(i + CHUNK_SIZE, ids.size());
            List<UUID> chunk = ids.subList(i, endIndex);
            drugRepository.deleteAllById(chunk);
        }
    }
} 