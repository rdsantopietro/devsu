package com.devsu.movimiento.service.messaging.impl;

import com.devsu.movimiento.service.messaging.MessagingSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessagingSendServiceKafkaImpl implements MessagingSendService {

    private final Logger log = LoggerFactory.getLogger(MessagingSendService.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void sendMessage(String queque, String message) {
        log.info("Sending message to: " + queque +". Message : " + message);
        kafkaTemplate.send(queque, message);
    }
}
