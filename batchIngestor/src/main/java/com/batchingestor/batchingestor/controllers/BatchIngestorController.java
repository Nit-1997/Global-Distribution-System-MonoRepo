package com.batchingestor.batchingestor.controllers;

import com.batchingestor.batchingestor.models.Response;
import com.batchingestor.batchingestor.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/ingest/batch")
@Slf4j
public class BatchIngestorController {
    private final StorageService storageService;

    @Autowired
    public BatchIngestorController(@Qualifier("GCP") StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/csv")
    public Response ingestCSV(@RequestParam("file")MultipartFile flightData){
        try{
            String publicFileURL = storageService.store(flightData);
            return new Response("Successfully Stored File to GCS", publicFileURL);
        }catch(Exception e){
            log.error(e.getMessage());
            return new Response("Failed to store File to GCS"+e.getMessage() , "Failure");
        }
    }
}
