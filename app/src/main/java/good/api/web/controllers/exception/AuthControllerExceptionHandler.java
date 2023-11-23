package good.api.web.controllers.exception;

import good.api.infrastructure.services.auth.exception.AuthServiceException;
import good.api.infrastructure.services.user.exception.UserServiceException;
import good.api.web.controllers.exception.body.ExceptionResponseBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice("good.api.web.controllers.auth")
public class AuthControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AuthServiceException.class)
    protected ResponseEntity<ExceptionResponseBody> handleAuthServiceExceptions(HttpServletRequest request, AuthServiceException exception) {
        final var body = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(UserServiceException.class)
    protected ResponseEntity<ExceptionResponseBody> handleUserServiceExceptions(HttpServletRequest request, UserServiceException exception) {
        final var body = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponseBody> handleExceptions(HttpServletRequest request, Exception exception) {
        final var body = new ExceptionResponseBody(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.internalServerError().body(body);
    }


}
