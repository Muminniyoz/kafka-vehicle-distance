package uz.epam.delivery.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.epam.delivery.dto.TaxiCoordinate;
import uz.epam.delivery.service.VehicleMessageService;

@RestController
@RequestMapping("/api/v1/coordinate")
@Slf4j
public class CoordinateResource {

    @Autowired
    VehicleMessageService messageService;

    @PostMapping()
    public void receiveCoordinate(@RequestBody TaxiCoordinate taxiCoordinate){
        log.info(taxiCoordinate.toString());
        messageService.sendMessage(taxiCoordinate);
    }
}
