package logistics.warehouse.service;

import logistics.warehouse.dto.WarehouseDTO;

public interface WarehouseService {
    void addWarehouseView(WarehouseDTO warehouseDTO);
    void updateWarehouseView(WarehouseDTO warehouseDTO);
    void deleteWarehouse(Long warehouseId);

}
