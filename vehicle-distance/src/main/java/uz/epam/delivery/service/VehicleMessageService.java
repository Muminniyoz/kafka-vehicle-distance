package uz.epam.delivery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import uz.epam.delivery.dto.TaxiCoordinate;
import uz.epam.delivery.dto.VehicleSession;

import java.io.Serializable;

@Service
public class VehicleMessageService implements Serializable {


    @Autowired
            VehicleService vehicleService;

    Logger logger = LoggerFactory.getLogger(VehicleMessageService.class.getName());

    @KafkaListener(topics = "${app.kafka.taxi_coordinate}", groupId = "vehicle-distance")
    public void listenGroupFoo(TaxiCoordinate taxiCoordinate) {
        logger.info("Received taxi coordinate: " + taxiCoordinate);
       var v =  vehicleService.getByVehicleId(taxiCoordinate.getTaxiId());
       if(v.isPresent()){
           v.get().changeDistance(taxiCoordinate.getLatitude(), taxiCoordinate.getLongitude());
           logger.info("Vehicle: " + v.get());
       } else {
           VehicleSession vh = new VehicleSession();
           vh.setId(taxiCoordinate.getTaxiId());
           vh.setLastLon(taxiCoordinate.getLongitude());
           vh.setLastLat(taxiCoordinate.getLatitude());
           vh.setDistance(0);
           vehicleService.create(vh);
           logger.info("Initialized vehicle: " + vh);
       }


    }


}
