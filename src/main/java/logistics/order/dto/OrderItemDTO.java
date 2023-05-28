package logistics.order.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long orderItemId;
    private Long orderId;
    private Long inventoryItemId;
    private String inventoryItemName;
    private int orderItemQuantity;
}
