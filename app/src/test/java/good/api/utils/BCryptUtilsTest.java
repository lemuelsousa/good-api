package good.api.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BCryptUtilsTest {

    @Test
    @DisplayName("Given string When Bcrypt Encode Then Return String Encoded")
    void test_GivenString_WhenBcryptEncode_ThenReturnStringEncoded() {
        final var password = "909090";
        final var encodedPassword = BCryptUtils.bcrypt().encode(password);

        assertNotNull(encodedPassword);
        assertNotEquals(password, encodedPassword,
                "Must not be equals");
    }

}