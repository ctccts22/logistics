package logistics.company.entity;

import jakarta.persistence.*;
import logistics.company.dto.CompanyDTO;
import logistics.inventory.entity.InventoryItem;
import logistics.order.entity.Order;
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

    @Column(name = "company_address", length = 50, nullable = true)
    private String companyAddress;

    @Column(name = "is_deleted")
    private String companyIsDeleted;

    @Column(name = "company_content", length = 255)
    private String companyContent;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Warehouse> warehouses;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<InventoryItem> inventoryItems;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Order> orders;

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

    @Builder(toBuilder = true)
    public Company(Long companyId, String companyName, CompanyType companyType, String companyLicense, String companyAddress, String companyIsDeleted, List<Warehouse> warehouses, List<InventoryItem> inventoryItems, List<Order> orders) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyType = companyType;
        this.companyLicense = companyLicense;
        this.companyAddress = companyAddress;
        this.companyIsDeleted = companyIsDeleted;
        this.warehouses = warehouses;
        this.inventoryItems = inventoryItems;
        this.orders = orders;
    }

    public void updateWith(CompanyDTO companyDTO) {
        this.companyName = companyDTO.getCompanyName();
        this.companyType = companyDTO.getCompanyType();
        this.companyLicense = companyDTO.getCompanyLicense();
        this.companyAddress = companyDTO.getCompanyAddress();
        this.companyContent = companyDTO.getCompanyContent();
    }

    public enum CompanyType {
        LOGISTICS_PROVIDER,
        CUSTOMER,
        SUPPLIER
    }

}


