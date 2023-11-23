package good.api.infrastructure.services.auth.dto;

public record LoginInput(
        String email,
        String password
) {
}
