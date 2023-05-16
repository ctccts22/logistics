package logistics.user.repository;

import logistics.user.entity.Role;

public interface RoleCustomRepository {

    Role findByRoleName(Role.RoleName roleName);
}