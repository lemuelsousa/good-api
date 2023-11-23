package good.api.infrastructure.services.user;

import good.api.domain.user.User;
import good.api.domain.user.role.UserRole;
import good.api.infrastructure.repositories.user.UserJpaRepository;
import good.api.infrastructure.repositories.user.model.UserJpaGateway;
import good.api.infrastructure.repositories.user.model.UserJpaModel;
import good.api.infrastructure.services.user.dto.UserServiceInput;
import good.api.infrastructure.services.user.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Mock
    UserJpaRepository userRepository;

    @Mock
    UserJpaGateway userJpaGateway;

    @InjectMocks
    UserServiceImpl userServiceImpl;

    User user;
    UserJpaModel userJpaModel;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        final var uuid = "2ea390e3-7739-4d60-a74b-9f098208cb76";

        final var id = UUID.fromString(uuid);
        final var name = "user";
        final var email = "user@example.com";
        final var password = "909090";
        final var role = UserRole.USER;
        final var createdAt = Instant.now();

        user = User.with(id, name, email, password, role);
        userJpaModel = UserJpaModel.with(id, name, email, password, role.getRole(), createdAt);
    }

    @Test
    @DisplayName("Given User When Register Then Register and Return User Successfully")
    void successfullyUserRegisterTest() {
        BDDMockito
                .when(userRepository.save(Mockito.any(UserJpaModel.class)))
                .thenReturn(userJpaModel);

        final var userServiceInput = new UserServiceInput(
        "user", "user@example.com", "909090");

        final var register = userServiceImpl.register(userServiceInput);

        assertNotNull(register, "Should not be null");
        assertEquals(UserRole.USER, register.userRole(), "User roles must be the same");
    }


}