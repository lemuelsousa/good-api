package good.api.infrastructure.services.token.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api.security.jwt")
public class JWTProperties {

    private String key;
    private Long expiration;
    private RefreshTokenProperties refreshToken;

    public String key() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long expiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public static class RefreshTokenProperties {
        private Long expiration;

        public Long expiration() {
            return expiration;
        }

        public void setExpiration(Long expiration) {
            this.expiration = expiration;
        }
    }

    public RefreshTokenProperties refreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(RefreshTokenProperties refreshToken) {
        this.refreshToken = refreshToken;
    }
}
