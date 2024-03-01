package com.kafka.controllers;


import com.kafka.model.ExRatesDTO;
import com.kafka.service.KafkaPublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakePublisherController {

    private final KafkaPublicationService kafkaPublicationService;

    public FakePublisherController(KafkaPublicationService kafkaPublicationService) {
        this.kafkaPublicationService = kafkaPublicationService;
    }
    @PostMapping("")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getExchangeRates(@RequestBody ExRatesDTO exRatesDTO) {
        boolean isPublish = this.kafkaPublicationService.publishRate(exRatesDTO);
        CustomResponse customResponse = new CustomResponse();
        if (isPublish) {
            customResponse.setCustom("Successful publish in kafka");
        } else {
            customResponse.setCustom("Unsuccessful publish in kafka");
        }
        return ResponseEntity.ok(customResponse);
    }
}
