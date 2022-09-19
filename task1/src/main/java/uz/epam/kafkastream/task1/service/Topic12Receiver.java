package uz.epam.kafkastream.task1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Slf4j
public class Topic12Receiver implements Serializable {
    @KafkaListener(topics = "${app.kafka.topic.task1_2}", groupId = "task1_2_consumer")
    public void listenGroupFoo(String msg) {
        log.info("Received: " + msg);
    }
}
