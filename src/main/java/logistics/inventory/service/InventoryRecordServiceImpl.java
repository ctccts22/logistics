package logistics.inventory.service;

import jakarta.persistence.EntityNotFoundException;
import logistics.inventory.dto.InventoryRecordDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.inventory.entity.InventoryRecord;
import logistics.inventory.repository.InventoryItemRepository;
import logistics.inventory.repository.InventoryRecordRepository;
import logistics.warehouse.entity.Warehouse;
import logistics.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class InventoryRecordServiceImpl implements InventoryRecordService {

    private final InventoryRecordRepository inventoryRecordRepository;
    private final InventoryItemRepository inventoryItemRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    @Transactional
    public void addInventoryRecord(InventoryRecordDTO inventoryRecordDTO) {
        InventoryItem inventoryItem = inventoryItemRepository.findByInventoryItemName(inventoryRecordDTO.getInventoryItemName());
        Warehouse warehouse = warehouseRepository.findByLocation(inventoryRecordDTO.getWarehouseLocation());

        InventoryRecord inventoryRecord = new InventoryRecord();
        inventoryRecord.setInventoryItem(inventoryItem);
        inventoryRecord.setWarehouse(warehouse);
        inventoryRecord.setInventoryRecordQuantity(inventoryRecordDTO.getInventoryRecordQuantity());
        inventoryRecord.setInventoryRecordLastUpdate(LocalDateTime.now());

        inventoryRecordRepository.save(inventoryRecord);
    }


    @Override
    @Transactional
    public void updateInventoryRecord(InventoryRecordDTO inventoryRecordDTO) {
        InventoryRecord existingRecord = inventoryRecordRepository.findById(inventoryRecordDTO.getInventoryRecordId())
                .orElseThrow(() -> new EntityNotFoundException("Inventory record not found with id: " + inventoryRecordDTO.getInventoryRecordId()));
        existingRecord.setInventoryRecordQuantity(inventoryRecordDTO.getInventoryRecordQuantity());

        inventoryRecordRepository.save(existingRecord);
    }
}
