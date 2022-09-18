package uz.epam.delivery.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import uz.epam.delivery.dto.VehicleSession;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class VehicleService {
        ArrayList<VehicleSession> database = new ArrayList<>();

        public Optional<VehicleSession> getByVehicleId(Long id){
            return database.stream().filter(v->v.getId().equals(id)).findFirst();
        }


    public void create(VehicleSession vh) {
            database.add(vh);
    }
}
