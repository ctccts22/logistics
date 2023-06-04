package logistics.delivery.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.delivery.dto.DeliveryDTO;
import logistics.delivery.entity.Delivery;
import logistics.delivery.repository.DeliveryRepository;
import logistics.shipment.entity.Shipment;
import logistics.shipment.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class DeliveryServiceImpl implements DeliveryService {

    private final ShipmentRepository shipmentRepository;
    private final DeliveryRepository deliveryRepository;

    @Override
    @Transactional
    public void addDeliveryView(DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findByShipmentId(deliveryDTO.getShipmentId());
        if (existingDelivery != null) {
            throw new IllegalArgumentException("A delivery already exists for shipment id: " + deliveryDTO.getShipmentId());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A delivery already exists for shipment id: " + deliveryDTO.getShipmentId());
        }
        Shipment existingShipment = shipmentRepository.findById(deliveryDTO.getShipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found with id: " + deliveryDTO.getShipmentId()));

        if (!existingShipment.getShipmentStatus().equals(Shipment.shipmentStatus.ARRIVED)) {
            throw new IllegalArgumentException("Cannot create delivery for shipment with status: " + existingShipment.getShipmentStatus());
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot create delivery for shipment with status: " + existingShipment.getShipmentStatus());
        }

        Delivery delivery = new Delivery();
        delivery.setShipment(existingShipment);
        delivery.setDeliveryDate(LocalDateTime.now());
        delivery.setDeliveryStatus(Delivery.deliveryStatus.DELIVERING);

        deliveryRepository.save(delivery);
    }

    @Override
    @Transactional
    public void updateDeliveryView(DeliveryDTO deliveryDTO) {
        Delivery existingDelivery = deliveryRepository.findById(deliveryDTO.getDeliveryId())
                .orElseThrow(() -> new EntityNotFoundException("Delivery not found with id: " + deliveryDTO.getDeliveryId()));

        if (!existingDelivery.getShipment().getShipmentId().equals(deliveryDTO.getShipmentId())) {
            throw new IllegalArgumentException("Cannot change shipment id of an existing delivery");
        }

        existingDelivery.setDeliveryDate(LocalDateTime.now());
        existingDelivery.setDeliveryStatus(deliveryDTO.getDeliveryStatus());

        deliveryRepository.save(existingDelivery);
    }
}