package logistics.order.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.company.entity.Company;
import logistics.company.repository.CompanyRepository;
import logistics.inventory.entity.InventoryRecord;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.inventory.repository.InventoryRecordRepository;
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
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CompanyRepository companyRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final InventoryRecordRepository inventoryRecordRepository;

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

//    @Override
//    public void updateOrderView(OrderDTO orderDTO) {
//        Order existingOrder = orderRepository.findById(orderDTO.getOrderId())
//                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderDTO.getOrderId()));
//
//        existingOrder.setOrderDeliveryDate(orderDTO.getOrderDeliveryDate());
//        existingOrder.setOrderStatus(orderDTO.getOrderStatus());
//
//        orderRepository.save(existingOrder);
//    }



    @Override
    @Transactional
    public void updateOrderView(OrderDTO orderDTO) {
        Order existingOrder = orderRepository.findById(orderDTO.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + orderDTO.getOrderId()));

        if (Order.orderStatus.DELIVERED.equals(orderDTO.getOrderStatus())) {
            List<OrderItem> orderItems = existingOrder.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                InventoryRecord inventoryRecord = inventoryRecordRepository.findByItemId(orderItem.getInventoryItem().getInventoryItemId())
                        .orElseThrow(() -> new EntityNotFoundException("Inventory record not found for item id: " + orderItem.getInventoryItem().getInventoryItemId()));
                if (inventoryRecord.getInventoryRecordQuantity() < orderItem.getOrderItemQuantity()) {
                    throw new RuntimeException("Not enough inventory for item id: " + orderItem.getInventoryItem().getInventoryItemId());
                }
                inventoryRecord.setInventoryRecordQuantity(inventoryRecord.getInventoryRecordQuantity() - orderItem.getOrderItemQuantity());
                inventoryRecordRepository.save(inventoryRecord);
            }
        }

        existingOrder.setOrderDeliveryDate(orderDTO.getOrderDeliveryDate());
        existingOrder.setOrderStatus(orderDTO.getOrderStatus());

        orderRepository.save(existingOrder);
    }

}