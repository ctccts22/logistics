package logistics.inventory.service;

import logistics.inventory.dto.InventoryItemDTO;

public interface InventoryItemService {
    void addInventoryItem(InventoryItemDTO inventoryItemDTO);
    void updateWarehouse(InventoryItemDTO inventoryItemDTO);

}
