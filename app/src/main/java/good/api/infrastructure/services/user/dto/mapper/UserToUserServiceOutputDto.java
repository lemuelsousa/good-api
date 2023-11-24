package good.api.infrastructure.services.user.dto.mapper;

import good.api.domain.user.User;
import good.api.infrastructure.services.user.dto.UserServiceOutputDto;

import java.util.function.Function;

public class UserToUserServiceOutputDto implements Function<User, UserServiceOutputDto> {
    @Override
    public UserServiceOutputDto apply(User input) {
        return new UserServiceOutputDto(
                input.name(),
                input.email(),
                input.userRole().name(),
                input.password()
        );
    }

    public static UserServiceOutputDto convert(User input) {
        return new UserToUserServiceOutputDto().apply(input);
    }
}
