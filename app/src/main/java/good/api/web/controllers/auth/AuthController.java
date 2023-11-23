package good.api.web.controllers.auth;

import good.api.infrastructure.services.auth.AuthService;
import good.api.infrastructure.services.token.JWTService;
import good.api.infrastructure.services.token.dto.TokenInput;
import good.api.infrastructure.services.user.UserService;
import good.api.web.controllers.auth.dto.*;
import good.api.web.controllers.auth.dto.mapper.LoginRequestToInput;
import good.api.web.controllers.auth.dto.mapper.SignupRequestToUserServiceInput;
import good.api.web.controllers.auth.dto.mapper.UserToSignupResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final JWTService tokenService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService, JWTService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody @Valid SignupRequest data) {
        final var user = SignupRequestToUserServiceInput.convert(data);
        final var result = userService.register(user);

        final var response = UserToSignupResponse.convert(result);

        return ResponseEntity.ok(response);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest data) {
        final var user = authService.validate(LoginRequestToInput.convert(data));
        final var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token.token()));
    }

    @PostMapping("validate-token")
    public ResponseEntity<ValidateResponse> validate(@RequestBody @Valid ValidateRequest data) {
        final var isValid = tokenService.validate(new TokenInput(data.token()));
        return ResponseEntity.ok(new ValidateResponse(isValid));
    }
}
