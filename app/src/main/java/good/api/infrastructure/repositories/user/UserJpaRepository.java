package good.api.infrastructure.repositories.user;

import java.util.UUID;

import good.api.infrastructure.repositories.user.model.UserJpaModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserJpaRepository extends JpaRepository<UserJpaModel, UUID>{
    UserJpaModel findByEmail(String email);
}
