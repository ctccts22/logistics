package logistics.user.repository;

import logistics.user.entity.User;

public interface UserCustomRepository {

    User findByUsername(String username);
}
