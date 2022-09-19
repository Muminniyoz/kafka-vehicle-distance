package uz.epam.kafkastream.task1.service;

import org.springframework.stereotype.Service;
import uz.epam.kafkastream.task1.dto.VehicleSession;

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
