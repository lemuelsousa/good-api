package good.api.web.controllers.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record ValidateRequest(
        @NotBlank(message = "Token is mandatory") String token
) {
}
