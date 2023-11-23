package good.api.web.controllers.auth.dto.mapper;

import good.api.infrastructure.services.auth.dto.LoginInput;
import good.api.web.controllers.auth.dto.LoginRequest;

import java.util.function.Function;

public class LoginRequestToInput implements Function<LoginRequest, LoginInput> {
    @Override
    public LoginInput apply(LoginRequest input) {
        return new LoginInput(
                input.email(),
                input.password()
        );
    }

    public static LoginInput convert(LoginRequest input) {
        return new LoginRequestToInput().apply(input);
    }
}
