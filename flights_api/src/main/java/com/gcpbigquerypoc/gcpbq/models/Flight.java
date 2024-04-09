package com.gcpbigquerypoc.gcpbq.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private String source;
    private String destination;
    private String arrivalDate;
    private String arrivalTime;
    private String departureDate;
    private String departureTime;
    private String arrivalTimestamp;
    private String departureTimestamp;
    private Double price;
}
