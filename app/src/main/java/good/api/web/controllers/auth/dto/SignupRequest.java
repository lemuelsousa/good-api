package good.api.web.controllers.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignupRequest(
        @NotBlank(message = "Name is mandatory") String name,
        @NotBlank(message = "Email is mandatory") @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password is mandatory") String password
) {
}
