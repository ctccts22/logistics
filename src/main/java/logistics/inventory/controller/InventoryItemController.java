package logistics.inventory.controller;

import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.dto.InventoryItemDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.inventory.service.InventoryItemService;
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
@RequestMapping("/inventory")
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;
    private final InventoryItemRepository inventoryItemRepository;
    private final CompanyRepository companyRepository;


    @GetMapping("/inventoryItemView")
    public String showInventoryItemViewForm(Model model) {
        List<InventoryItem> inventoryItems = inventoryItemRepository.findAll();
        List<Company> companies = companyRepository.findAll();

        List<String> companyNames = companies
                .stream()
                .map(Company::getCompanyName)
                .collect(Collectors.toList());

        model.addAttribute("companyNames", companyNames);
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
        inventoryItemService.updateInventoryItem(inventoryItemDTO);
        return "redirect:/inventory/inventoryItemView";
    }

    @PostMapping("/deleteInventoryItemView")
    public String deleteInventoryItem(@RequestParam Long inventoryItemId) {
        inventoryItemService.deleteInventoryItem(inventoryItemId);
        return "redirect:/inventory/inventoryItemView";
    }


}
