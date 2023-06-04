package logistics.order.repository;

import logistics.order.dto.OrderViewDTO;
import logistics.order.entity.Order;

import java.util.List;

public interface OrderCustomRepository {

    List<OrderViewDTO> fetchAllOrdersWithOrderItems();

    Order findByOrderId(Long orderId);

}
