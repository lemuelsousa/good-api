package good.api.infrastructure.services.user.dto;

public record UserServiceInput(
        String name,
        String email,
        String passwor
) {
}
