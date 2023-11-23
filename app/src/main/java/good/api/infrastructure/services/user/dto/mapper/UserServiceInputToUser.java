package good.api.infrastructure.services.user.dto.mapper;

import good.api.domain.user.User;
import good.api.infrastructure.services.user.dto.UserServiceInput;

import java.util.function.Function;

public class UserServiceInputToUser implements Function<UserServiceInput, User> {
    @Override
    public User apply(UserServiceInput input) {
        return User.build(
                input.name(),
                input.email(),
                input.passwor()
        );
    }

    public static User convert(UserServiceInput input) {
        return new UserServiceInputToUser().apply(input);
    }
}
