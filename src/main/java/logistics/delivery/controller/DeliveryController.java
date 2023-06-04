package logistics.delivery.controller;

import logistics.delivery.dto.DeliveryDTO;
import logistics.delivery.entity.Delivery;
import logistics.delivery.repository.DeliveryRepository;
import logistics.delivery.service.DeliveryService;
import logistics.order.entity.Order;
import logistics.shipment.entity.Shipment;
import logistics.shipment.repository.ShipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryRepository deliveryRepository;
    private final ShipmentRepository shipmentRepository;

    @GetMapping("/deliveryView")
    public String showDeliveryView(Model model) {
        List<Shipment> shipments = shipmentRepository.findAll();
        List<Delivery> deliveries =deliveryRepository.findAll();

        List<Long> shipmentIds = shipments
                .stream()
                .map(Shipment::getShipmentId)
                .collect(Collectors.toList());

        model.addAttribute("title", "배송관리");
        model.addAttribute("shipmentIds", shipmentIds);
        model.addAttribute("deliveries", deliveries);

        return "delivery/delivery_view";
    }
    @PostMapping("/addDeliveryView")
    @ResponseBody
    public String addDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.addDeliveryView(deliveryDTO);
        log.info("deliveryDTO :{}", deliveryDTO);
        return "redirect:/delivery/deliveryView";
    }
    @PostMapping("/updateDeliveryView")
    @ResponseBody
    public String updateDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        deliveryService.updateDeliveryView(deliveryDTO);
        log.info("deliveryDTO :{}", deliveryDTO);
        return "redirect:/delivery/deliveryView";
    }
}
