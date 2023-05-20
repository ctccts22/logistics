package logistics.inventory.service;

import logistics.inventory.dto.InventoryItemDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.repository.InventoryItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService{
    private final InventoryItemRepository inventoryItemRepository;

    @Override
    @Transactional
    public void addInventoryItem(InventoryItemDTO inventoryItemDTO) {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setInventoryItemName(inventoryItemDTO.getInventoryItemName());
        inventoryItem.setInventoryItemDescription(inventoryItemDTO.getInventoryItemDescription());

        inventoryItemRepository.save(inventoryItem);
    }

    @Override
    @Transactional
    public void updateWarehouse(InventoryItemDTO inventoryItemDTO) {
        InventoryItem existingInventoryItem = inventoryItemRepository.findByInventoryItemId(inventoryItemDTO.getInventoryItemId());
        if (existingInventoryItem == null) {
            throw new RuntimeException("InventoryItem not found");
        }
        existingInventoryItem.setInventoryItemName(inventoryItemDTO.getInventoryItemName());
        existingInventoryItem.setInventoryItemDescription(inventoryItemDTO.getInventoryItemDescription());

        inventoryItemRepository.save(existingInventoryItem);
    }
}
