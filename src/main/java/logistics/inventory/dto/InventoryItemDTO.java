package logistics.inventory.dto;

import lombok.Data;

@Data
public class InventoryItemDTO {

    private Long inventoryItemId;
    private String inventoryItemName;
    private String inventoryItemDescription;

}
