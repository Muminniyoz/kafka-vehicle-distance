package uz.epam.kafkastream.task1.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;

@Component
@Slf4j
public class Streams implements Serializable {

    @Value(value = "${app.kafka.topic.task1_1}")
    private String topicTask11;
    @Value(value = "${app.kafka.topic.task1_2}")
    private String topicTask12;

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream(topicTask11, Consumed.with(STRING_SERDE, STRING_SERDE));
        messageStream.to(topicTask12);
    }
}
