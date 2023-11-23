package good.api.infrastructure.services.token.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import good.api.domain.user.User;
import good.api.infrastructure.services.token.JWTService;
import good.api.infrastructure.services.token.dto.TokenInput;
import good.api.infrastructure.services.token.dto.TokenSubjectOutput;
import good.api.infrastructure.services.token.dto.TokenOutput;
import good.api.infrastructure.services.token.exception.JWTServiceException;
import good.api.infrastructure.services.token.properties.JWTProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class JWTServiceImpl implements JWTService {

    private final JWTProperties jwtProperties;
    @Value("${api.issuer}")
    private String issuer;

    @Autowired
    public JWTServiceImpl(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Override
    public TokenOutput generateToken(User user) {
        try {
            final var token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(user.email())
                    .withExpiresAt(calculateExpiration(jwtProperties.expiration()))
                    .sign(algorithm());
            return new TokenOutput(token);
        }
        catch (IllegalArgumentException | JWTCreationException e) {
            throw new JWTServiceException(e.getMessage());
        }
    }

    @Override
    public TokenSubjectOutput getSubject(TokenInput token) {
        try {
            final var verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build()
                    .verify(token.token());

            return new TokenSubjectOutput(verifier.getSubject());
        }
        catch (Exception e) {
            return new TokenSubjectOutput("");
        }
    }

    @Override
    public boolean validate(TokenInput token) {
        return !getSubject(token)
                .subject().isBlank();
    }

    private Algorithm algorithm(){
        return Algorithm.HMAC256(jwtProperties.key());
    }

    private Instant calculateExpiration(long secAmount) {
        return Instant.now().plusSeconds(secAmount);
    }
}
