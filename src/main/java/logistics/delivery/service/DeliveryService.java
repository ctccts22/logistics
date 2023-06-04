package logistics.delivery.service;

import logistics.delivery.dto.DeliveryDTO;

public interface DeliveryService {

    void addDeliveryView(DeliveryDTO deliveryDTO);

    void updateDeliveryView(DeliveryDTO deliveryDTO);

}
