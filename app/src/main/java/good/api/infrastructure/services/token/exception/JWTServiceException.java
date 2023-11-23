package good.api.infrastructure.services.token.exception;

public class JWTServiceException extends RuntimeException {
    public JWTServiceException(String message) {
        super(message);
    }
}
