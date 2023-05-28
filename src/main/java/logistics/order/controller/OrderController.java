package logistics.order.controller;

import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.repository.InventoryItemCustomRepository;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.order.dto.OrderDTO;
import logistics.order.dto.OrderItemDTO;
import logistics.order.dto.OrderViewDTO;
import logistics.order.repository.OrderCustomRepository;
import logistics.order.repository.OrderRepository;
import logistics.order.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {
    private final InventoryItemRepository inventoryItemRepository;
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final CompanyRepository companyRepository;

    @GetMapping("/orderView")
    public String showOrderViewForm(Model model) {
        // left join
        List<OrderViewDTO> orders = ((OrderCustomRepository) orderRepository).fetchAllOrdersWithOrderItems();
        List<Company> companies = companyRepository.findAll();
        List<InventoryItem> inventoryItems = inventoryItemRepository.findAll();

        List<String> companyNames = companies
                .stream()
                .map(Company::getCompanyName)
                .collect(Collectors.toList());

        List<String> inventoryItemNames = inventoryItems
                .stream()
                .map(InventoryItem::getInventoryItemName)
                .collect(Collectors.toList());

        model.addAttribute("orders", orders);
        model.addAttribute("inventoryItemNames", inventoryItemNames);
        model.addAttribute("companyNames", companyNames);
        model.addAttribute("title", "주문관리");
        return "order/order_view";
    }
    @PostMapping("/addOrderView")
    @ResponseBody
    public String addOrder(@RequestBody OrderDTO orderDTO) {
        log.info("orderDTO1 :{}", orderDTO);
        orderService.addOrderView(orderDTO);
        log.info("orderDTO2 :{}", orderDTO);
        return "redirect:/order/orderView";
    }


}
