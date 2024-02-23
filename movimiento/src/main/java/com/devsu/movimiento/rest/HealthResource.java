package com.devsu.movimiento.rest;

import com.devsu.movimiento.service.messaging.MessagingSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing Health
 */
@RestController
@RequestMapping("/health")
public class HealthResource {

    private final Logger log = LoggerFactory.getLogger(HealthResource.class);

    @Autowired
    private MessagingSendService messagingService;

    @Value("${application.name}")
    private String applicationName;

    @GetMapping("")
    public ResponseEntity<String> getHealth() {
        log.debug("REST request to get a health status of MS");
        return ResponseEntity.ok().body(applicationName + " is ok!");
    }

    @GetMapping("/kafka/send")
    public ResponseEntity<String> sendMessage(@RequestParam String message, @RequestParam String queque) {
        log.info("REST request to get a health status of MS");
        messagingService.sendMessage(queque,message);
        return ResponseEntity.ok().body(applicationName + " is ok!");
    }

}
