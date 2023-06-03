package logistics.vehicle.service;

import logistics.vehicle.dto.VehicleDTO;
import logistics.vehicle.entity.Vehicle;
import logistics.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;


    @Override
    @Transactional
    public void addVehicleView(VehicleDTO vehicleDTO) {

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleDTO.getVehicleType());
        vehicle.setVehicleStatus(vehicleDTO.getVehicleStatus());

        vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void updateVehicleView(VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findByVehicleId(vehicleDTO.getVehicleId());
        if (existingVehicle == null) {
            throw new RuntimeException("Warehouse not found");
        }
        existingVehicle.setVehicleType(vehicleDTO.getVehicleType());
        existingVehicle.setVehicleStatus(vehicleDTO.getVehicleStatus());
        vehicleRepository.save(existingVehicle);
    }
}
