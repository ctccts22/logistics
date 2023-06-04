package logistics.delivery.repository;

import logistics.delivery.entity.Delivery;

public interface DeliveryCustomRepository {
    Delivery findByShipmentId(Long shipmentId);

    Delivery findByDeliveryId(Long deliveryId);

}
