package com.kafka.service;

import com.kafka.model.ExRatesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.kafka.config.KafkaConfig.EXCHANGE_RATE_TOPIC;

@Service
public class KafkaPublicationService {

    private final Logger LOGGER = LoggerFactory.getLogger(KafkaPublicationService.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaPublicationService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public boolean publishRate(ExRatesDTO exRatesDTO) {
        CompletableFuture<SendResult<String, Object>> sendResultCompletableFuture = kafkaTemplate
                .send(EXCHANGE_RATE_TOPIC, exRatesDTO)
                .whenComplete(
                (res, ex) -> {
                    if (ex == null) {
                        LOGGER.info(
                                "Kafka message successfully send to topic {}/partition {}/ offset {}. Key = {}/Headers = {}.",
                                res.getRecordMetadata().topic(),
                                res.getRecordMetadata().partition(),
                                res.getRecordMetadata().offset(),
                                res.getProducerRecord().key(),
                                res.getProducerRecord().headers()
                        );
                    } else {
                        LOGGER.error("Problem with the publication to kafka.", ex);
                    }
                }

        );
        return !sendResultCompletableFuture.isCancelled();
    }
}
