package logistics.user.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    @ToString.Exclude
    private Role role;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "phone", length = 15, nullable = false)
    private String phone;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @PrePersist
    protected void onCreate() {
        this.creationDate = LocalDateTime.now();
        this.isDeleted = (this.isDeleted == null) ? "N" : this.isDeleted;
    }

    @Builder
    public User(Long id, String username, String password, Role role, String email, String phone, String isDeleted, LocalDateTime deleteDate, LocalDateTime creationDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.isDeleted = isDeleted;
        this.deleteDate = deleteDate;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
