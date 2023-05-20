package logistics.inventory.controller;

import logistics.inventory.dto.InventoryItemDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.inventory.service.InventoryItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/inventory")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;
    private final InventoryItemRepository inventoryItemRepository;


    @GetMapping("/inventoryItemView")
    public String showInventoryItemViewForm(Model model) {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findAll();

        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("title", "재고관리");
        return "inventory/inventoryItem_view";
    }

    @PostMapping("/addInventoryItemView")
    public String addInventoryItem(@ModelAttribute("inventoryItem") InventoryItemDTO inventoryItemDTO) {
        inventoryItemService.addInventoryItem(inventoryItemDTO);
        return "redirect:/inventory/inventoryItemView";
    }

    @PostMapping("/updateInventoryItemView")
    public String updateWarehouse(@ModelAttribute("inventoryItem") InventoryItemDTO inventoryItemDTO) {
        inventoryItemService.updateWarehouse(inventoryItemDTO);
        return "redirect:/inventory/inventoryItemView";
    }



}
