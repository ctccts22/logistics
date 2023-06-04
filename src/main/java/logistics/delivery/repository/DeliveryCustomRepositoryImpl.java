package logistics.delivery.repository;

import logistics.delivery.entity.Delivery;
import logistics.delivery.entity.QDelivery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class DeliveryCustomRepositoryImpl extends QuerydslRepositorySupport implements DeliveryCustomRepository {

    private static final QDelivery qDelivery = QDelivery.delivery;

    public DeliveryCustomRepositoryImpl() {
        super(Delivery.class);
    }


    @Override
    public Delivery findByShipmentId(Long shipmentId) {
        return from(qDelivery)
                .select(qDelivery)
                .where(qDelivery.shipment.shipmentId.eq(shipmentId))
                .fetchOne();
    }

    @Override
    public Delivery findByDeliveryId(Long deliveryId) {
        return from(qDelivery)
                .select(qDelivery)
                .where(qDelivery.deliveryId.eq(deliveryId))
                .fetchOne();
    }
}
