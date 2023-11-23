package good.api.web.controllers.auth.dto;

public record SignupResponse(
        String name,
        String email,
        String password
) {
}
