package logistics.shipment.service;

import logistics.shipment.dto.ShipmentDTO;

public interface ShipmentService {
    void addShipmentView(ShipmentDTO shipmentDTO);

    void updateShipmentView(ShipmentDTO shipmentDTO);

}
