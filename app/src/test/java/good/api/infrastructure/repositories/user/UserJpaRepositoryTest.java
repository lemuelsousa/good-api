package good.api.infrastructure.repositories.user;

import good.api.domain.user.User;
import good.api.domain.user.role.UserRole;
import good.api.infrastructure.repositories.user.model.UserJpaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserJpaRepositoryTest {

    @Autowired
    UserJpaRepository userRepository;

    UserJpaModel userJpaModel;

    @BeforeEach
    void setup() {
        final var id = UUID.randomUUID();
        final var name = "user";
        final var email = "user@example.com";
        final var password = "909090";
        final var role = UserRole.USER;
        final var createdAt = Instant.now();

        userJpaModel = UserJpaModel.with(id, name, email, password, role.getRole(), createdAt);
    }

    @Test
    @DisplayName("Given Email When Find By Email Then Return User JPA Model")
    void successfullyFindByEmailTest() {
        final var email = "user@example.com";

        userRepository.save(userJpaModel);
        final var result = userRepository.findByEmail(email);

        assertNotNull(result, "Must return a User JPA Model");
        assertEquals(email, result.email(),
                "User emails must be the same");
    }

}