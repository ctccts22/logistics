package logistics.warehouse.repository;

import logistics.warehouse.entity.Warehouse;

public interface WarehouseCustomRepository {

    Warehouse findByWarehouseId(Long warehouseId);
}
