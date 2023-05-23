package logistics.inventory.repository;

import logistics.inventory.entity.InventoryRecord;
import logistics.inventory.entity.QInventoryRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class InventoryRecordCustomRepositoryImpl extends QuerydslRepositorySupport implements InventoryRecordCustomRepository {

    private static final QInventoryRecord qInventoryRecord = QInventoryRecord.inventoryRecord;

    public InventoryRecordCustomRepositoryImpl() {
        super(InventoryRecord.class);
    }


}
