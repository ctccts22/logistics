package logistics.order.repository;

import logistics.order.entity.OrderItem;
import logistics.order.entity.QOrderItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class OrderItemCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderItemCustomRepository {

    private static final QOrderItem qOrderItem = QOrderItem.orderItem;

    public OrderItemCustomRepositoryImpl() {
        super(OrderItem.class);
    }


}
