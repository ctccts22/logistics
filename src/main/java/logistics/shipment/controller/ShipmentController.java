package logistics.shipment.controller;

import logistics.order.entity.Order;
import logistics.order.repository.OrderRepository;
import logistics.shipment.dto.ShipmentDTO;
import logistics.shipment.entity.Shipment;
import logistics.shipment.repository.ShipmentRepository;
import logistics.shipment.service.ShipmentService;
import logistics.vehicle.entity.Vehicle;
import logistics.vehicle.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/shipment")
public class ShipmentController {
    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final ShipmentService shipmentService;
    private final ShipmentRepository shipmentRepository;

    @GetMapping("/shipmentView")
    public String showShipmentView(Model model) {
        List<Shipment> shipments = shipmentRepository.findAll();
        List<Vehicle> vehicles = vehicleRepository.findAll();
        List<Order> orders = orderRepository.findAll();

        List<String> vehiclesTypes = vehicles
                .stream()
                .map(Vehicle::getVehicleType)
                .collect(Collectors.toList());

        List<Long> orderIds = orders
                .stream()
                .map(Order::getOrderId)
                .collect(Collectors.toList());

        model.addAttribute("vehiclesTypes", vehiclesTypes);
        model.addAttribute("orderIds", orderIds);
        model.addAttribute("shipments", shipments);
        model.addAttribute("title", "산적관리");

        return "shipment/shipment_view";
    }
    @PostMapping("/addShipmentView")
    @ResponseBody
    public String addShipment(@RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.addShipmentView(shipmentDTO);
        log.info("shipmentDTO :{}", shipmentDTO);
        return "redirect:/shipment/shipmentView";
    }
    @PostMapping("/updateShipmentView")
    @ResponseBody
    public String updateShipment(@RequestBody ShipmentDTO shipmentDTO) {
        shipmentService.updateShipmentView(shipmentDTO);
        log.info("shipmentDTO :{}", shipmentDTO);
        return "redirect:/shipment/shipmentView";
    }
}
