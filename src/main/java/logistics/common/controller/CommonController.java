package logistics.common.controller;

import logistics.order.dto.OrderDTO;
import logistics.order.entity.Order;
import logistics.order.repository.OrderRepository;
import logistics.shipment.dto.ShipmentDTO;
import logistics.shipment.entity.Shipment;
import logistics.shipment.repository.ShipmentRepository;
import logistics.vehicle.dto.VehicleDTO;
import logistics.vehicle.entity.Vehicle;
import logistics.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class CommonController {
	private final ShipmentRepository shipmentRepository;
	private final VehicleRepository vehicleRepository;
	private final OrderRepository orderRepository;

	@GetMapping("/")
	public String mainPage() {
		return "main";
	}
	@GetMapping("/api/shipments")
	@ResponseBody
	public List<ShipmentDTO> getShipments() {
		List<Shipment> shipments = shipmentRepository.findAll();
		List<ShipmentDTO> shipmentDTOs = new ArrayList<>();
		for (Shipment shipment : shipments) {
			ShipmentDTO shipmentDTO = new ShipmentDTO();
			shipmentDTO.setShipmentId(shipment.getShipmentId());
			shipmentDTO.setOrderId(shipment.getOrder().getOrderId());
			shipmentDTO.setVehicleId(shipment.getVehicle().getVehicleId());
			shipmentDTO.setDepartureDate(shipment.getDepartureDate());
			shipmentDTO.setArrivalDate(shipment.getArrivalDate());
			shipmentDTO.setShipmentStatus(shipment.getShipmentStatus());
			shipmentDTOs.add(shipmentDTO);
		}
		return shipmentDTOs;
	}

	@GetMapping("/api/vehicles")
	@ResponseBody
	public List<VehicleDTO> getVehicles() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		List<VehicleDTO> vehicleDTOs = new ArrayList<>();
		for (Vehicle vehicle : vehicles) {
			VehicleDTO vehicleDTO = new VehicleDTO();
			vehicleDTO.setVehicleId(vehicle.getVehicleId());
			vehicleDTO.setVehicleType(vehicle.getVehicleType());
			vehicleDTO.setVehicleStatus(vehicle.getVehicleStatus());
			vehicleDTOs.add(vehicleDTO);
		}
		return vehicleDTOs;
	}

	@GetMapping("/api/orders")
	@ResponseBody
	public List<OrderDTO> getOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderDTOs = new ArrayList<>();
		for (Order order : orders) {
			OrderDTO orderDTO = new OrderDTO();
			orderDTO.setOrderId(order.getOrderId());
			orderDTO.setCompanyName(order.getCompany().getCompanyName());
			orderDTO.setOrderDate(order.getOrderDate());
			orderDTO.setOrderDeliveryDate(order.getOrderDeliveryDate());
			orderDTO.setOrderStatus(order.getOrderStatus());
			orderDTOs.add(orderDTO);
		}
		return orderDTOs;
 	}

}
