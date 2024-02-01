package com.ivzilol.kafka.controllers;


import com.ivzilol.kafka.model.ExRatesDTO;
import com.ivzilol.kafka.model.ExchangeRatesDTO;
import com.ivzilol.kafka.service.KafkaPublicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
public class FakePublisherController {

    private final KafkaPublicationService kafkaPublicationService;

    public FakePublisherController(KafkaPublicationService kafkaPublicationService) {
        this.kafkaPublicationService = kafkaPublicationService;
    }

    @GetMapping("/publish")
    public String publish() {
        var toPublish = new ExchangeRatesDTO(
                "USD",
                System.currentTimeMillis(),
                Map.of("BGN", BigDecimal.valueOf(1.840515),
                        "EUR", BigDecimal.valueOf(0.937668)
                )
        );

        kafkaPublicationService.publishExchangeRate(toPublish);
        return "OK";
    }

    @PostMapping("")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<?> getExchangeRates(@RequestBody ExRatesDTO exRatesDTO) {
        boolean isPublish = this.kafkaPublicationService.publishRate(exRatesDTO);
        CustomResponse customResponse = new CustomResponse();
        if (!isPublish) {
            customResponse.setCustom("Successful publish in kafka");
        } else {
            customResponse.setCustom("Unsuccessful publish in kafka");
        }
        return ResponseEntity.ok(customResponse);
    }
}
