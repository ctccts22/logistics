package logistics.delivery.dto;

import logistics.delivery.entity.Delivery;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryDTO {
    private Long deliveryId;
    private Long shipmentId;
    private LocalDateTime deliveryDate;
    private Delivery.deliveryStatus deliveryStatus;
}
