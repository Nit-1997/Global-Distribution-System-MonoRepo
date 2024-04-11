package com.example.ingestorrt.services;

import com.example.ingestorrt.models.FlightMessage;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway(defaultRequestChannel = "pubSubOutuputChannel")
public interface PubSubOutboundGateway {
    void sendToPubsub(Message<String> flight);
}
