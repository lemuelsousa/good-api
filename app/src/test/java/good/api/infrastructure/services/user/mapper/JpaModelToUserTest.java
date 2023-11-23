package good.api.infrastructure.services.user.mapper;

import good.api.domain.user.role.UserRole;
import good.api.infrastructure.repositories.user.model.UserJpaModel;
import good.api.infrastructure.repositories.user.model.mapper.JpaModelToUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class JpaModelToUserTest {

    @Test
    @DisplayName("Given User JPA Model When Convert To User Then Return User Successfully")
    void successfullyConvertJpaModelToUserTest() {
        final var id = UUID.randomUUID();
        final var name = "user";
        final var email = "user@example.com";
        final var password = "909090";
        final var role = UserRole.USER.getRole().toUpperCase();
        final var createdAt = Instant.now();

        final var userJpaModel = UserJpaModel.with(id, name, email, password, role, createdAt);
        final var convertedUser = JpaModelToUser.convert(userJpaModel);

        assertNotNull(convertedUser,
                "User must not be null");
        assertNotNull(convertedUser.id(),
                "The converted user id must not be null");
        assertEquals(userJpaModel.id(), convertedUser.id(),
                "User Id must be the same User JPA Model Id");
    }

}