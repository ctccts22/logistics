package logistics.inventory.repository;


import logistics.inventory.entity.InventoryRecord;

import java.util.Optional;

public interface InventoryRecordCustomRepository {
    Optional<InventoryRecord> findByItemId(Long inventoryItemId);

}
