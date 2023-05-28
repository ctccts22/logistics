package logistics.inventory.entity;


import jakarta.persistence.*;
import logistics.warehouse.entity.Warehouse;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "inventory_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long inventoryRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @ToString.Exclude
    private InventoryItem inventoryItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    @ToString.Exclude
    private Warehouse warehouse;

    @Column(name = "quantity", nullable = false)
    private int inventoryRecordQuantity;

    @Column(name = "last_update")
    private LocalDateTime inventoryRecordLastUpdate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InventoryRecord inventoryRecord = (InventoryRecord) o;
        return Objects.equals(inventoryRecordId, inventoryRecord.inventoryRecordId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryRecordId);
    }

}
