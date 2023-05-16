package logistics.user.repository;

import logistics.user.entity.QUser;
import logistics.user.entity.User;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

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
}
