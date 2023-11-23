package good.api.infrastructure.services.token.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JWTPropertiesTest {

    private final JWTProperties jwtProperties;

    @Autowired
    JWTPropertiesTest(JWTProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    @Test
    public void testJwtProperties() {
        assertNotNull(jwtProperties);
        assertEquals("k3nd0-9slkl-nl4kl-jn34l-ksd89-67sdf-sdg00-sdddd", jwtProperties.key());
        assertEquals(3600, jwtProperties.expiration());

        JWTProperties.RefreshTokenProperties refreshTokenProperties = jwtProperties.refreshToken();
        assertNotNull(refreshTokenProperties);
        assertEquals(604800, refreshTokenProperties.expiration());
    }
}