package com.trantor.tcomm.orderservice.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @KafkaListener(topics = "order-request")
    public void consumeMessage(String message){
        System.out.println("Received: " + message);
    }
}
