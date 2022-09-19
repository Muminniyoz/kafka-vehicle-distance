package uz.epam.kafkastream.task1.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class StreamsPipeline implements Serializable {

    @Value(value = "${app.kafka.topic.task1_1}")
    private String topicTask11;
    @Value(value = "${app.kafka.topic.task1_2}")
    private String topicTask12;

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Bean
    public Map<String, KStream<String, String>> buildPipeline(StreamsBuilder streamsBuilder) {
        return streamsBuilder
                .stream(topicTask11, Consumed.with(STRING_SERDE, STRING_SERDE))
                .peek((key, value)->log.info("Stream handle: key: " + key + ", value: " + value))
                .filter((key, value)-> key == null)
                .mapValues((key, value)-> value.split(" "))
                .flatMap((key, value) ->
                         Arrays
                                 .stream(value)
                                 .map(v -> new KeyValue<>(v.length()+"", v))
                                 .collect(Collectors.toList())
                )
                .split(Named.as("words-"))
                .branch((key, value)->value.length()>=10, Branched.as("long"))
                .branch((key, value)->value.length()<10, Branched.as("short"))
                .defaultBranch();
    }

    @Bean
    public KStream<String,String> mergeStream(Map<String, KStream<String,String>> map){
        return map.values().stream()
                  .map(streams -> streams.filter((k, v) -> v.contains("a")))
                .reduce((a,b)->a.merge(b)).get();
    }




}
