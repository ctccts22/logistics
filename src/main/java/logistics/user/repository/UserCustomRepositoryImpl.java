package logistics.user.repository;

import logistics.user.entity.QUser;
import logistics.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

@Slf4j
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements UserCustomRepository {
    private static final QUser qUser = QUser.user;

    public UserCustomRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByUsername(String username) {
        return from(qUser)
                .select(qUser).
                where(qUser.username.eq(username))
                .fetchOne();
    }

    @Override
    public boolean existsByUsername(String username) {
        long count = from(qUser)
                .select(qUser)
                .where(qUser.username.eq(username))
                .fetchCount();
        log.info("username : {}", username);
        log.info("counting username : {}", count);
        return count > 0; // username이 존재하면 true
    }
}
