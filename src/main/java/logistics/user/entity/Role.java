package logistics.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;


    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false, unique = true)
    private RoleName roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<User> users;

    @Override
    public String toString() {
        return roleName.toString();
    }


    @Builder
    public Role(Long roleId, RoleName roleName, List<User> users) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.users = users;
    }

    public enum RoleName {
        ADMIN,
        MANAGER,
        EMPLOYEE
    }

}
