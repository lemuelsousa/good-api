package good.api.web.controllers.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ValidateResponse(
        @JsonProperty("is_valid") boolean isValid
) {
}
