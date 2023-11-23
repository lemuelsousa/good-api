package good.api.web.controllers.exception.body;

import java.time.Instant;

public record ExceptionResponseBody(
        Instant timestamp,
        Integer status,
        String error,
        String path
) {
}
