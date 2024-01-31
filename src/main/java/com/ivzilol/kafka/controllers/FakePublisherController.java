package com.ivzilol.kafka.controllers;


import com.ivzilol.kafka.model.ExchangeRatesDTO;
import com.ivzilol.kafka.service.KafkaPublicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
