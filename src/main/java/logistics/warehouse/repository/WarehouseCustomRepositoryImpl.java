package logistics.warehouse.repository;


import logistics.warehouse.entity.QWarehouse;
import logistics.warehouse.entity.Warehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Slf4j
public class WarehouseCustomRepositoryImpl extends QuerydslRepositorySupport implements WarehouseCustomRepository {

    private static final QWarehouse qWarehouse = QWarehouse.warehouse;

    public WarehouseCustomRepositoryImpl() {
        super(Warehouse.class);
    }

    @Override
    public Warehouse findByWarehouseId(Long warehouseId) {
        return from(qWarehouse)
                .select(qWarehouse)
                .where(qWarehouse.warehouseId.eq(warehouseId))
                .fetchOne();
    }

    @Override
    public Warehouse findByLocation(String warehouseLocation) {
        return from(qWarehouse)
                .select(qWarehouse)
                .where(qWarehouse.location.eq(warehouseLocation))
                .fetchFirst();
    }
}
