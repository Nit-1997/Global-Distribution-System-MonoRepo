package com.gcpbigquerypoc.gcpbq.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class FlightResponse {
    private String status;
    private int count;
    private List<Flight> flights;
}
