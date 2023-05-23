package logistics.company.entity;

import jakarta.persistence.*;
import logistics.inventory.entity.InventoryItem;
import logistics.user.entity.Role;
import logistics.warehouse.entity.Warehouse;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name", length = 50, nullable = false)
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_type", nullable = false)
    private Company.CompanyType companyType;

    @Column(name = "company_license", length = 50, nullable = false)
    private String companyLicense;

    @Column(name = "company_address", length = 50, nullable = false)
    private String companyAddress;

    @Column(name = "is_deleted")
    private String companyIsDeleted;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Warehouse> warehouses;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<InventoryItem> inventoryItems;

    @PrePersist
    protected void onCreate() {
        this.companyIsDeleted = String.valueOf('N');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId);
    }

    public enum CompanyType {
        LOGISTICS_PROVIDER,
        CUSTOMER,
        SUPPLIER
    }

}


