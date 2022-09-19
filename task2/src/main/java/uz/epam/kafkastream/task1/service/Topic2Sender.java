package uz.epam.kafkastream.task1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
@EnableScheduling
@Slf4j
public class Topic2Sender implements Serializable {

    @Value(value = "${app.kafka.topic.task2}")
    private String topicTask2;
    int value = 1;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostConstruct
    public void sendMessage() {
        String message = "1234 1234567891011a 12345654321122ab aslfjsdlfjsldf sdfls dflsdjaflsdjflsdjflsdjflsajfsjdlfslf sdlfjdlsfjsl sljflsfj";
        kafkaTemplate.send(topicTask2, message);
    }
}
