package logistics.user.repository;

import logistics.user.entity.QRole;
import logistics.user.entity.Role;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class RoleCustomRepositoryImpl extends QuerydslRepositorySupport implements RoleCustomRepository {
    private static final QRole qRole = QRole.role;

    public RoleCustomRepositoryImpl() {
        super(Role.class);
    }

    @Override
    public Role findByRoleName(Role.RoleName roleName) {
        return from(qRole)
                .select(qRole)
                .where(qRole.roleName.eq(roleName))
                .fetchOne();
    }
}