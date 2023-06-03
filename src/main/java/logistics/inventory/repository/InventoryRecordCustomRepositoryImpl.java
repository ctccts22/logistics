package logistics.inventory.repository;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import logistics.inventory.entity.InventoryRecord;
import logistics.inventory.entity.QInventoryItem;
import logistics.inventory.entity.QInventoryRecord;
import logistics.warehouse.entity.QWarehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

@Slf4j
public class InventoryRecordCustomRepositoryImpl extends QuerydslRepositorySupport implements InventoryRecordCustomRepository {

    private static final QInventoryRecord qInventoryRecord = QInventoryRecord.inventoryRecord;


    public InventoryRecordCustomRepositoryImpl() {
        super(InventoryRecord.class);
    }


    @Override
    public Optional<InventoryRecord> findByItemId(Long inventoryItemId) {
        QInventoryRecord inventoryRecord = QInventoryRecord.inventoryRecord;
        QInventoryItem inventoryItem = QInventoryItem.inventoryItem;
        JPQLQuery<InventoryRecord> query = from(inventoryRecord)
                .where(inventoryRecord.inventoryItem.eq(inventoryItem)
                        .and(inventoryItem.inventoryItemId.eq(inventoryItemId)));
        InventoryRecord result = query.fetchOne();
        return Optional.ofNullable(result);
    }

}
