package logistics.order.repository;

import com.querydsl.core.types.Projections;
import logistics.company.entity.QCompany;
import logistics.inventory.entity.QInventoryItem;
import logistics.order.dto.OrderViewDTO;
import logistics.order.entity.Order;
import logistics.order.entity.QOrder;
import logistics.order.entity.QOrderItem;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class OrderCustomRepositoryImpl extends QuerydslRepositorySupport implements OrderCustomRepository {

    private static final QOrder qOrder = QOrder.order;

    public OrderCustomRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<OrderViewDTO> fetchAllOrdersWithOrderItems() {
        QOrder qOrder = QOrder.order;
        QCompany qCompany = QCompany.company;
        QOrderItem qOrderItem = QOrderItem.orderItem;
        QInventoryItem qInventoryItem = QInventoryItem.inventoryItem;

        return from(qOrder)
                .leftJoin(qOrder.orderItems, qOrderItem)
                .leftJoin(qOrder.company, qCompany)
                .leftJoin(qOrderItem.inventoryItem, qInventoryItem)
                .select(Projections.constructor(OrderViewDTO.class,
                        qOrder.orderId,
                        qCompany.companyName,
                        qOrder.orderDate,
                        qOrder.orderDeliveryDate,
                        qOrder.orderStatus,
                        qInventoryItem.inventoryItemName,
                        qOrderItem.orderItemQuantity))
                .fetch();
    }

}
