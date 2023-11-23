package good.api.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import good.api.domain.user.role.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("Given data When Build User Then Build Successfully")
    void successfullyBuildUserTest() {
        final var name = "user";
        final var email = "user@example.com";
        final var password = "909090";

        final var user = User.build(name, email, password);

        assertNotNull(user, "Should not be null");
        assertEquals(UserRole.USER, user.userRole(),
                "The created user role must return the 'USER' role");
        assertNotNull(user.id(),
                "The created user id must not return null");

    }

}