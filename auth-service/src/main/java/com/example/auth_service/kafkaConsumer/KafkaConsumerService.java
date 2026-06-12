package com.example.auth_service.kafkaConsumer;



import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service // Tells Spring Boot to run this in the background
public class KafkaConsumerService {

    // This annotation is the magic. It acts like your API endpoint,
    // but instead of listening for HTTP requests, it listens to the Kafka topic.
    @KafkaListener(topics = "user-login-events", groupId = "auth-service-group")
    public void consumeLoginEvent(String message) {

        // When a message hits Kafka, Spring Boot's Deserializer turns it into this 'message' String.
        // We just vomit it to the console exactly like you wanted.

        System.out.println("🚨 [AUTH SERVICE] CAUGHT EVENT FROM KAFKA: " + message);

        // Later, we can add logic here to check Redis or send an email!
    }
}
