package com.example.ingestorrt.controllers;

import com.example.ingestorrt.models.FlightMessage;
import com.example.ingestorrt.models.IngestorPublishResponse;
import com.example.ingestorrt.services.IngestorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/ingestor")
public class IngestorController {
    @Autowired
    IngestorService ingestorService;
    @PostMapping("/publish")
    public IngestorPublishResponse ingestPublish(@RequestBody FlightMessage newFlight){
        log.info("Start publish job to GCP Pub/Sub...");
        return ingestorService.publishFlight(newFlight);
    }

}
