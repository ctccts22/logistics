package logistics.vehicle.dto;

import logistics.vehicle.entity.Vehicle;
import lombok.Data;

@Data
public class VehicleDTO {
    private Long vehicleId;
    private String vehicleType;
    private Vehicle.vehicleStatus vehicleStatus;
}
