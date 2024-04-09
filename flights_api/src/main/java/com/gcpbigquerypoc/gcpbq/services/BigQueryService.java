package com.gcpbigquerypoc.gcpbq.services;

import com.gcpbigquerypoc.gcpbq.models.Flight;
import com.gcpbigquerypoc.gcpbq.models.FlightResponse;
import com.google.cloud.bigquery.BigQuery;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.QueryJobConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class BigQueryService {

    @Autowired
    BigQuery bigquery;
    public static String projectID = "my-project-1509111052665";
    public static String dataset  = "flights_dataset";
    public static String table = "schedule_table";

    private Map.Entry<String, String> dateParser(String timestamp){
        SimpleDateFormat dateFormatUTC = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormatUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

        String parsedTimestamp = dateFormatUTC.format(new Date(Double.valueOf(timestamp).longValue() * 1000));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime dateTime = LocalDateTime.parse(parsedTimestamp, formatter);

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);
        return new AbstractMap.SimpleEntry<>(date,time);
    }

    public FlightResponse getFlights(){
        log.info("Started basic query service...");

        String query = String.format("SELECT * FROM `%s.%s.%s` LIMIT 1000",projectID,dataset,table);
        QueryJobConfiguration queryConfig =
                QueryJobConfiguration.newBuilder(query).build();
        FlightResponse response = new FlightResponse();
        List<Flight> flightList = new ArrayList<>();
        int flightCnt = 0;
        try{
            for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {
                Flight proccessingFlight = new Flight();
                String arrivalTimestamp = row.get("arrival_time").getValue().toString();
                Map.Entry<String , String> arrParsedDateTime = dateParser(arrivalTimestamp);
                String departTimestamp = row.get("departure_time").getValue().toString();
                Map.Entry<String , String> deptParsedDateTime = dateParser(departTimestamp);

                proccessingFlight.setSource(row.get("source").getValue().toString());
                proccessingFlight.setDestination(row.get("destination").getValue().toString());
                proccessingFlight.setPrice(Double.parseDouble(row.get("price").getValue().toString()));
                proccessingFlight.setArrivalTimestamp(arrivalTimestamp);
                proccessingFlight.setDepartureTimestamp(departTimestamp);
                proccessingFlight.setArrivalDate(arrParsedDateTime.getKey());
                proccessingFlight.setArrivalTime(arrParsedDateTime.getValue());
                proccessingFlight.setDepartureDate(deptParsedDateTime.getKey());
                proccessingFlight.setDepartureTime(deptParsedDateTime.getValue());

                flightCnt++;
                flightList.add(proccessingFlight);
            }
        }catch (InterruptedException e){
            log.info("Exception occured during big query job: ");
            log.info(e.getMessage());
            response.setCount(0);
            response.setFlights(new ArrayList<>());
            response.setStatus("FAILED");
            return response;
        }

        response.setStatus("SUCCESS");
        response.setCount(flightCnt);
        response.setFlights(flightList);
       return response;
    }
}
