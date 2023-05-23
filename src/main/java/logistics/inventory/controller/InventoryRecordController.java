package logistics.inventory.controller;

import logistics.inventory.service.InventoryRecordService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/inventory")
public class InventoryRecordController {

    private final InventoryRecordService inventoryRecordService;


}
