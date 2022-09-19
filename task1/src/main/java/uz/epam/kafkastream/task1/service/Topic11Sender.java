package uz.epam.kafkastream.task1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@EnableScheduling
@Slf4j
public class Topic11Sender implements Serializable {

    @Value(value = "${app.kafka.topic.task1_1}")
    private String topicTask11;
    int value = 1;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 2000)
    public void sendMessage() {
        log.info("Message: " + ++value);
        kafkaTemplate.send(topicTask11, "Message: " + value);
    }
}
