package logistics.order.entity;

import jakarta.persistence.*;
import logistics.company.entity.Company;
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
@AllArgsConstructor
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

    public enum orderStatus {
        PENDING,
        PROCESSING,
        DELIVERED
    }
}
