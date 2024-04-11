package com.example.ingestorrt;

import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@SpringBootApplication
public class IngestorRtApplication {

    public static void main(String[] args) {
        SpringApplication.run(IngestorRtApplication.class, args);
    }

    @Bean
    @ServiceActivator(inputChannel = "pubSubOutuputChannel")
    public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, "flights_topic");
    }

}
