package logistics.inventory.service;

import logistics.inventory.dto.InventoryRecordDTO;

public interface InventoryRecordService {
    void addInventoryRecord(InventoryRecordDTO inventoryRecordDTO);

    void updateInventoryRecord(InventoryRecordDTO inventoryRecordDTO);

}
