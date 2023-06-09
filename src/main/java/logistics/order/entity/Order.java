package logistics.order.entity;

import jakarta.persistence.*;
import logistics.company.entity.Company;
import logistics.shipment.entity.Shipment;
import lombok.*;
import org.hibernate.Hibernate;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    @ToString.Exclude
    private Company company;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "delivery_date")
    private Date orderDeliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Order.orderStatus orderStatus;

    // order update 발생하는 에러 해결
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Shipment> shipments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    @Builder
    public Order(Long orderId, Company company, Date orderDate, Date orderDeliveryDate, Order.orderStatus orderStatus, List<OrderItem> orderItems, List<Shipment> shipments) {
        this.orderId = orderId;
        this.company = company;
        this.orderDate = orderDate;
        this.orderDeliveryDate = orderDeliveryDate;
        this.orderStatus = orderStatus;
        this.orderItems = orderItems;
        this.shipments = shipments;
    }

    public enum orderStatus {
        PENDING,
        PROCESSING,
        DELIVERED
    }
}
