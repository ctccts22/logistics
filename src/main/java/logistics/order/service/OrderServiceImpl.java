package logistics.order.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.order.dto.OrderDTO;
import logistics.order.dto.OrderItemDTO;
import logistics.order.entity.Order;
import logistics.order.entity.OrderItem;
import logistics.order.repository.OrderItemRepository;
import logistics.order.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public void addOrderView(OrderDTO orderDTO) {

        Company company = companyRepository.findByCompanyName(orderDTO.getCompanyName());
        if (company == null) {
            throw new RuntimeException("Company not found");
        }

        Order order = new Order();
        order.setCompany(company);
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setOrderStatus(Order.orderStatus.PENDING);

        List<OrderItem> orderItems = new ArrayList<>();

        if (orderDTO.getOrderItemDTOS() == null) {
            throw new RuntimeException("OrderItemDTOS is null");
        }

        for (OrderItemDTO itemDTO : orderDTO.getOrderItemDTOS()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);

            item.setInventoryItem(inventoryItemRepository.findByInventoryItemName(itemDTO.getInventoryItemName()));
                if (itemDTO.getInventoryItemName().isEmpty()) {
                    throw new EntityNotFoundException("Item not found");
                }

            item.setOrderItemQuantity(itemDTO.getOrderItemQuantity());

            orderItems.add(item);
        }

        order.setOrderItems(orderItems);

        orderRepository.save(order);
    }
}