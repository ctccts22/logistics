package logistics.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "inventory_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long inventoryItemId;

    @Column(name = "item_name", length = 50, nullable = false)
    private String inventoryItemName;

    @Column(name = "item_description", nullable = true)
    private String inventoryItemDescription;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InventoryItem inventoryItem = (InventoryItem) o;
        return Objects.equals(inventoryItemId, inventoryItem.inventoryItemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryItemId);
    }
}
