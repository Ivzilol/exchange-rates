package com.kafka.consumer;

import com.kafka.model.ExRatesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExRatesConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExRatesConsumer.class);

    private final Set<ExRatesDTO> message = new HashSet<>();

    @KafkaListener(
            topics = "exchange_rates",
            groupId = "ivzilol-consumer"
    )
    public void readMessage(
            @Header(KafkaHeaders.RECEIVED_KEY) String topicKey,
                            ExRatesDTO exRate){
        LOGGER.info("We have consumed ex rate with key {} value {}.", topicKey ,exRate);
        System.out.println(exRate.getCurrency());
        exRate.getRates().forEach((key, value) -> System.out.println(key  + " -> " + value));
        message.add(exRate);
    }

    public Set<ExRatesDTO> getMessage() {
        Set<ExRatesDTO> currentMessage = new HashSet<>(message);
        if (message.size() > 1) {
            message.clear();
        }
        return currentMessage;
    }
}
