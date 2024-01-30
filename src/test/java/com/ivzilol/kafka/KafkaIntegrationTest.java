package com.ivzilol.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9099", "port=9099" })
public class KafkaIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage;

    @Test
    public void testKafkaIntegration() throws Exception {
        String topic = "test-topic";
        String message = "Hello, Kafka!";

        // Изпращане на съобщение
        kafkaTemplate.send(topic, message);

        // Очакване на обработката на съобщението от KafkaListener
        latch.await(10, TimeUnit.SECONDS);

        // Проверка дали KafkaListener е получил съобщението
        assertEquals(message, receivedMessage);
    }

    @KafkaListener(topics = "test-topic", groupId = "test-group")
    public void listen(String message) {
        // Обработка на полученото съобщение
        receivedMessage = message;
        latch.countDown();
    }
}
