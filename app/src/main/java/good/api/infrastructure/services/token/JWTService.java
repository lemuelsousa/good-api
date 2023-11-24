package good.api.infrastructure.services.token;

import good.api.infrastructure.services.token.dto.TokenInput;
import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {

    String generateAccessToken(UserDetails user);
    String generateRefreshToken(UserDetails user);
    String getSubject(TokenInput token);
    boolean validate(TokenInput token);

}
