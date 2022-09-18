package uz.epam.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import uz.epam.delivery.dto.TaxiCoordinate;

import java.util.Random;

@SpringBootApplication
@EnableScheduling
public class DeliveryApplicationGateway {
    public static void main(String[] args) {
        SpringApplication.run(DeliveryApplicationGateway.class, args);
    }

    RestTemplate restTemplate = new RestTemplate();
    Logger logger = LoggerFactory.getLogger(DeliveryApplicationGateway.class.getName());
    Random random = new Random();
    @Scheduled(fixedDelay = 100)
    public void sendRequest(){

        TaxiCoordinate taxiCoordinate = new TaxiCoordinate();
        taxiCoordinate.setTaxiId(Math.abs(random.nextLong() % 10) + 1);
        taxiCoordinate.setLatitude((30+taxiCoordinate.getTaxiId()) +Math.random());
        taxiCoordinate.setLongitude((64+taxiCoordinate.getTaxiId()) +Math.random());
        logger.info(taxiCoordinate.toString());
        restTemplate.postForEntity("http://localhost:8081/api/v1/coordinate", taxiCoordinate, String.class);
    }
}