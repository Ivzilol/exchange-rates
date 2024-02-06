package com.kafka.controller;

import com.kafka.consumer.ExRatesConsumer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageController {

    private final ExRatesConsumer exRatesConsumer;

    public MessageController(ExRatesConsumer exRatesConsumer) {
        this.exRatesConsumer = exRatesConsumer;
    }

    @GetMapping("")
    @CrossOrigin(origins = "http://localhost:3001")
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.of(Optional.ofNullable(exRatesConsumer.getMessage()));
    }
}
