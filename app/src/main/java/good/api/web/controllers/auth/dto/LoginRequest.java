package good.api.web.controllers.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password is mandatory") String password
) {
}
