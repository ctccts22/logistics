package logistics.inventory.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryRecordDTO {

    private Long inventoryRecordId;
    private Long inventoryItemId;
    private Long warehouseId;
    private String inventoryRecordQuantity;
    private LocalDateTime inventoryRecordLastUpdate;
}
