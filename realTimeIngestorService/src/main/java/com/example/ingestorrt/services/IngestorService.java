package com.example.ingestorrt.services;

import com.example.ingestorrt.models.FlightMessage;
import com.example.ingestorrt.models.IngestorPublishResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IngestorService {

    @Autowired
    private PubSubOutboundGateway messagingGateway;
    public IngestorPublishResponse publishFlight(FlightMessage flight) {
        log.info("Pushing new flight : "+flight.toString()+" to Outbound Channel ...");
        try{
            String flightJson = flight.toJson();
            messagingGateway.sendToPubsub(MessageBuilder.withPayload(flightJson).build());
            return new IngestorPublishResponse("Success");
        }catch(Exception e){
            log.error(e.getMessage());
            return new IngestorPublishResponse(e.getMessage());
        }
    }
}
