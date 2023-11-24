package good.api.infrastructure.services.user.dto;

public record UserServiceOutputDto(
        String name,
        String email,
        String role,
        String password

) {
}
