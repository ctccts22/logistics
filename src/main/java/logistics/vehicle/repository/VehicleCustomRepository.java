package logistics.vehicle.repository;

import logistics.vehicle.entity.Vehicle;

public interface VehicleCustomRepository {
    Vehicle findByVehicleId(Long vehicleId);

    Vehicle findByVehicleType(String vehicleType);

}
