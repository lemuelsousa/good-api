package good.api.infrastructure.repositories.user.model.mapper;

import good.api.domain.user.User;
import good.api.infrastructure.repositories.user.model.UserJpaModel;

import java.util.function.Function;

public class UserToJpaModel implements Function<User, UserJpaModel> {
    @Override
    public UserJpaModel apply(User user) {
        return UserJpaModel.build(
                user.id(),
                user.name(),
                user.email(),
                user.password(),
                user.userRole().getRole()
        );
    }

    public static UserJpaModel convert(User user) {
        return new UserToJpaModel().apply(user);
    }
}