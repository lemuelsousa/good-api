package good.api.infrastructure.services.token.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import good.api.infrastructure.services.token.JWTService;
import good.api.infrastructure.services.token.dto.TokenInput;
import good.api.infrastructure.services.token.exception.JWTServiceException;
import good.api.infrastructure.services.token.properties.JWTProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;

@Service
public class JWTServiceImpl implements JWTService {

    private final JWTProperties jwtProperties;
    @Value("${api.issuer}")
    private String issuer;

    @Autowired
    public JWTServiceImpl(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    private String createToken(String subject,
        Collection< ? extends GrantedAuthority> authorities, Instant expiration) {
        try {
            var token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withExpiresAt(expiration);

            if (authorities != null)
                token.withClaim("role", authorities
                        .stream()
                        .map((GrantedAuthority::getAuthority)).toList());

            return token.sign(algorithm());
        }
        catch (IllegalArgumentException | JWTCreationException e) {
            throw new JWTServiceException(e.getMessage());
        }
    }

    @Override
    public String generateAccessToken(UserDetails user) {
        final var expiration = calculateExpiration(jwtProperties.expiration());
        return createToken(user.getUsername(), user.getAuthorities(), expiration);
    }

    @Override
    public String generateRefreshToken(UserDetails user) {
        final var expiration = calculateExpiration(jwtProperties.refreshToken().expiration());
        return createToken(user.getUsername(), null, expiration);
    }

    @Override
    public String getSubject(TokenInput token) {
        try {
            final var verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build()
                    .verify(token.token());

            return verifier.getSubject();
        }
        catch (Exception e) {
            return "";
        }
    }

    @Override
    public boolean validate(TokenInput token) {
        return !getSubject(token).isBlank();
    }

    private Algorithm algorithm(){
        return Algorithm.HMAC256(jwtProperties.key());
    }

    private Instant calculateExpiration(long secAmount) {
        return Instant.now().plusSeconds(secAmount);
    }
}
