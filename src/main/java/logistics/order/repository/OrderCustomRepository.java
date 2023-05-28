package logistics.order.repository;

import logistics.order.dto.OrderViewDTO;

import java.util.List;

public interface OrderCustomRepository {

    public List<OrderViewDTO> fetchAllOrdersWithOrderItems();
}
