package logistics.shipment.repository;

import logistics.shipment.entity.QShipment;
import logistics.shipment.entity.Shipment;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class ShipmentCustomRepositoryImpl extends QuerydslRepositorySupport implements ShipmentCustomRepository {

    private static final QShipment qShipment = QShipment.shipment;

    public ShipmentCustomRepositoryImpl() {
        super(Shipment.class);
    }

}
