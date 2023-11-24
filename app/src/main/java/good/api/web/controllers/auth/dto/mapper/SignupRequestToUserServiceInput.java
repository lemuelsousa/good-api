package good.api.web.controllers.auth.dto.mapper;

import good.api.infrastructure.services.user.dto.UserServiceInput;
import good.api.web.controllers.auth.dto.SignupRequest;

import java.util.function.Function;

public class SignupRequestToUserServiceInput implements Function<SignupRequest, UserServiceInput> {
    public static UserServiceInput convert(SignupRequest input) {
        return new SignupRequestToUserServiceInput().apply(input);
    }

    @Override
    public UserServiceInput apply(SignupRequest input) {
        return new UserServiceInput(
                input.name(),
                input.email(),
                input.password()
        );
    }
}
