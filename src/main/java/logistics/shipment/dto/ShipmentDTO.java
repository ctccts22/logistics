package logistics.shipment.dto;

import logistics.shipment.entity.Shipment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShipmentDTO {
    private Long shipmentId;
    private Long orderId;
    private Long vehicleId;
    private String vehicleType;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Shipment.shipmentStatus shipmentStatus;
}
