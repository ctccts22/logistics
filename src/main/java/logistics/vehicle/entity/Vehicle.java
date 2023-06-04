package logistics.vehicle.entity;

import jakarta.persistence.*;
import logistics.order.entity.Order;
import logistics.shipment.entity.Shipment;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Long vehicleId;

    @Column(name = "vehicle_type", nullable = false)
    private String vehicleType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status", nullable = false)
    private Vehicle.vehicleStatus vehicleStatus;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Shipment> shipments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleId, vehicle.vehicleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId);
    }

    public enum vehicleStatus {
        AVAILABLE,
        IN_USE,
        UNDER_MAINTENANCE
    }
}
