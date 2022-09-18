package uz.epam.delivery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.epam.delivery.dto.TaxiCoordinate;

import java.io.Serializable;

@Service
public class VehicleMessageService implements Serializable {

    @Value(value = "${app.kafka.taxi_coordinate}")
    private String taxiCoordinateTopic;

    @Autowired
    private KafkaTemplate<String, TaxiCoordinate> kafkaTemplate;

    public void sendMessage(TaxiCoordinate coordinate) {
        kafkaTemplate.send(taxiCoordinateTopic, coordinate);
    }
}
