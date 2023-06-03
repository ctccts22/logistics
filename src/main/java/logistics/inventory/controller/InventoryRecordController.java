package logistics.inventory.controller;

import logistics.company.entity.Company;
import logistics.inventory.dto.InventoryRecordDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.entity.InventoryRecord;
import logistics.inventory.repository.InventoryItemCustomRepository;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.inventory.repository.InventoryRecordRepository;
import logistics.inventory.service.InventoryRecordService;
import logistics.warehouse.entity.Warehouse;
import logistics.warehouse.repository.WarehouseCustomRepository;
import logistics.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/inventory")
public class InventoryRecordController {

    private final InventoryRecordService inventoryRecordService;
    private final InventoryRecordRepository inventoryRecordRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final WarehouseRepository warehouseRepository;


    @GetMapping("/inventoryRecordView")
    public String showInventoryRecordViewForm(Model model) {
        List<InventoryRecord> inventoryRecords = inventoryRecordRepository.findAll();
        List<InventoryItem> inventoryItems = inventoryItemRepository.findAll();
        List<Warehouse> warehouses = warehouseRepository.findAll();

        List<String> inventoryItemNames = inventoryItems
                .stream()
                .map(InventoryItem::getInventoryItemName)
                .collect(Collectors.toList());

        List<String> warehouseLocations = warehouses
                .stream()
                .map(Warehouse::getLocation)
                .collect(Collectors.toList());

        log.info("inventoryRecords :{}", inventoryRecords);
        log.info("inventoryItemNames :{}", inventoryItemNames);
        log.info("warehouseLocations :{}", warehouseLocations);
        model.addAttribute("inventoryRecords", inventoryRecords);
        model.addAttribute("inventoryItemNames", inventoryItemNames);
        model.addAttribute("warehouseLocations", warehouseLocations);
        model.addAttribute("inventoryItems", inventoryItems);
        model.addAttribute("warehouses", warehouses);
        model.addAttribute("title", "재고기록관리");
        return "inventory/inventoryRecord_view";
    }

    @PostMapping("/addInventoryRecordView")
    public String addInventoryRecord(@ModelAttribute("inventoryRecord") InventoryRecordDTO inventoryRecordDTO) {
        log.info("inventoryRecordDTO :{}", inventoryRecordDTO);
        inventoryRecordService.addInventoryRecord(inventoryRecordDTO);
        return "redirect:/inventory/inventoryRecordView";
    }

    @PostMapping("/updateInventoryRecordView")
    public String updateInventoryRecord(@ModelAttribute("inventoryRecord") InventoryRecordDTO inventoryRecordDTO) {
        inventoryRecordService.updateInventoryRecord(inventoryRecordDTO);
        return "redirect:/inventory/inventoryRecordView";
    }
}
