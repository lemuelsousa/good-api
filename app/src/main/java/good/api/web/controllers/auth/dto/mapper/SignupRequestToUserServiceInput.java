package good.api.web.controllers.auth.dto.mapper;

import good.api.domain.user.User;
import good.api.infrastructure.services.user.dto.UserServiceInput;
import good.api.web.controllers.auth.dto.SignupRequest;

import java.util.function.Function;

public class SignupRequestToUserServiceInput implements Function<SignupRequest, UserServiceInput> {
    @Override
    public UserServiceInput apply(SignupRequest input) {
        return new UserServiceInput(
                input.name(),
                input.email(),
                input.password()
        );
    }

    public static UserServiceInput convert(SignupRequest input) {
        return new SignupRequestToUserServiceInput().apply(input);
    }
}
