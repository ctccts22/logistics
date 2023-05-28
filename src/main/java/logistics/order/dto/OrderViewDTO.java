package logistics.order.dto;

import logistics.order.entity.Order;
import lombok.Data;

import java.sql.Date;

@Data
public class OrderViewDTO {
    private Long orderId;
    private String companyName;
    private Date orderDate;
    private Date orderDeliveryDate;
    private Order.orderStatus orderStatus;
    private String inventoryItemName;
    private int orderItemQuantity;


    public OrderViewDTO(Long orderId, String companyName, Date orderDate, Date orderDeliveryDate, Order.orderStatus orderStatus, String inventoryItemName, int orderItemQuantity) {
        this.orderId = orderId;
        this.companyName = companyName;
        this.orderDate = orderDate;
        this.orderDeliveryDate = orderDeliveryDate;
        this.orderStatus = orderStatus;
        this.inventoryItemName = inventoryItemName;
        this.orderItemQuantity = orderItemQuantity;
    }
}
