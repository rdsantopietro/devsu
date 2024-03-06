package com.devsu.movimiento.service.messaging.impl;

import com.devsu.movimiento.service.messaging.MessagingListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "messaging.kafka", havingValue = "true", matchIfMissing = false)
public class NotificationMessagingListenerServiceImpl implements MessagingListenerService {

    private final Logger log = LoggerFactory.getLogger(NotificationMessagingListenerServiceImpl.class);

    @Autowired
    private ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactoryM;

    @Override
    @KafkaListener( id = "myListener", topics = "new-account")
    public void listenMessage(String message) {
        log.info("Listening message: " + message);
    }
}
