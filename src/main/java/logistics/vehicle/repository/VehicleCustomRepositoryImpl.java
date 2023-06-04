package logistics.vehicle.repository;

import logistics.vehicle.entity.QVehicle;
import logistics.vehicle.entity.Vehicle;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class VehicleCustomRepositoryImpl extends QuerydslRepositorySupport implements VehicleCustomRepository {

    private static final QVehicle qVehicle = QVehicle.vehicle;

    public VehicleCustomRepositoryImpl() {
        super(Vehicle.class);
    }

    @Override
    public Vehicle findByVehicleId(Long vehicleId) {
        return from(qVehicle)
                .select(qVehicle)
                .where(qVehicle.vehicleId.eq(vehicleId))
                .fetchOne();
    }

    @Override
    public Vehicle findByVehicleType(String vehicleType) {
        return from(qVehicle)
                .select(qVehicle)
                .where(qVehicle.vehicleType.eq(vehicleType))
                .fetchOne();
    }
}
