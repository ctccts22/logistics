package logistics.user.repository;

import logistics.user.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> findByEmail(String email);

    User findByUsername(String username);

    boolean existsByUsername(String username);

}
