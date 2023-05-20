package logistics.inventory.repository;

import logistics.inventory.entity.InventoryItem;
import logistics.inventory.entity.QInventoryItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class InventoryItemCustomRepositoryImpl extends QuerydslRepositorySupport implements InventoryItemCustomRepository {

    private static final QInventoryItem qInventoryItem = QInventoryItem.inventoryItem;

    public InventoryItemCustomRepositoryImpl() {
        super(InventoryItem.class);
    }


    @Override
    public InventoryItem findByInventoryItemId(Long inventoryItemId) {
        return from(qInventoryItem)
                .select(qInventoryItem)
                .where(qInventoryItem.inventoryItemId.eq(inventoryItemId))
                .fetchOne();
    }
}
