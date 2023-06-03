package logistics.warehouse.repository;

import logistics.warehouse.entity.Warehouse;

import java.util.List;

public interface WarehouseCustomRepository {

    Warehouse findByWarehouseId(Long warehouseId);

    Warehouse findByLocation(String warehouseLocation);
}
