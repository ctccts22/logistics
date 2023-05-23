package logistics.warehouse.entity;

import jakarta.persistence.*;
import logistics.company.entity.Company;
import logistics.inventory.entity.InventoryRecord;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "warehouses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @ToString.Exclude
    private Company company;

    @Column(name = "location", length = 100, nullable = false)
    private String location;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<InventoryRecord> inventoryRecords;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Warehouse warehouse = (Warehouse) o;
        return Objects.equals(warehouseId, warehouse.warehouseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouseId);
    }

}
