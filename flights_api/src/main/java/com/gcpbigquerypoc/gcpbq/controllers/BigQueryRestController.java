package com.gcpbigquerypoc.gcpbq.controllers;

import com.gcpbigquerypoc.gcpbq.models.FlightResponse;
import com.gcpbigquerypoc.gcpbq.services.BigQueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class BigQueryRestController {

    @Autowired
    BigQueryService bigQueryService;

    @GetMapping("/getFlights")
    public FlightResponse getFlights(){
        log.info("Starting get flights API request...");
        return bigQueryService.getFlights();
    }
}
