package logistics.order.dto;

import logistics.order.entity.Order;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long orderId;
    private Long companyId;
    private String companyName;
    private Date orderDate;
    private Date orderDeliveryDate;
    private Order.orderStatus orderStatus;
    private List<OrderItemDTO> orderItemDTOS;
}
