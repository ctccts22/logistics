package logistics.shipment.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.company.entity.Company;
import logistics.order.entity.Order;
import logistics.order.repository.OrderCustomRepository;
import logistics.order.repository.OrderRepository;
import logistics.shipment.dto.ShipmentDTO;
import logistics.shipment.entity.Shipment;
import logistics.shipment.repository.ShipmentRepository;
import logistics.vehicle.entity.Vehicle;
import logistics.vehicle.repository.VehicleCustomRepository;
import logistics.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;

    @Override
    @Transactional
    public void addShipmentView(ShipmentDTO shipmentDTO) {
        Order order = orderRepository.findByOrderId(shipmentDTO.getOrderId());
        if (order == null) {
            throw new EntityNotFoundException("order not found");
        }
        Vehicle vehicle = vehicleRepository.findByVehicleType(shipmentDTO.getVehicleType());
        if (vehicle == null) {
            throw new EntityNotFoundException("vehicle not found");
        }
        Shipment shipment = new Shipment();
        shipment.setOrder(order);
        shipment.setVehicle(vehicle);
        shipment.setDepartureDate(LocalDateTime.now());
        shipment.setShipmentStatus(shipmentDTO.getShipmentStatus());

        shipmentRepository.save(shipment);
    }

    @Override
    @Transactional
    public void updateShipmentView(ShipmentDTO shipmentDTO) {
        Shipment existingShipment = shipmentRepository.findById(shipmentDTO.getShipmentId())
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found with id: " + shipmentDTO.getShipmentId()));
        existingShipment.setArrivalDate(shipmentDTO.getArrivalDate());
        existingShipment.setShipmentStatus(shipmentDTO.getShipmentStatus());
        shipmentRepository.save(existingShipment);


    }
}
