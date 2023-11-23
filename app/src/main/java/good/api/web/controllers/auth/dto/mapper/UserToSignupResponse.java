package good.api.web.controllers.auth.dto.mapper;

import good.api.domain.user.User;
import good.api.web.controllers.auth.dto.SignupResponse;

import java.util.function.Function;

public class UserToSignupResponse implements Function<User, SignupResponse> {
    @Override
    public SignupResponse apply(User input) {
        return new SignupResponse(
                input.name(),
                input.email(),
                input.password()
        );
    }

    public static SignupResponse convert(User input) {
        return new UserToSignupResponse().apply(input);
    }
}
