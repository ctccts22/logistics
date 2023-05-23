package logistics.inventory.repository;

import logistics.inventory.entity.InventoryItem;

public interface InventoryItemCustomRepository {

    InventoryItem findByInventoryItemId(Long inventoryItemId);

    InventoryItem findByInventoryItemName(String inventoryItemName);

}
