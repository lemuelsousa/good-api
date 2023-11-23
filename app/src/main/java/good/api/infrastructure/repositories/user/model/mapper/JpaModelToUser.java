package good.api.infrastructure.repositories.user.model.mapper;

import good.api.domain.user.User;
import good.api.domain.user.role.UserRole;
import good.api.infrastructure.repositories.user.model.UserJpaModel;

import java.util.function.Function;

public class JpaModelToUser implements Function<UserJpaModel, User> {
    @Override
    public User apply(UserJpaModel input) {
        return User.with(
                input.id(),
                input.name(),
                input.email(),
                input.password(),
                UserRole.valueOf(input.userRole())
        );
    }

    public static User convert(UserJpaModel input) {
        return new JpaModelToUser().apply(input);
    }
}
