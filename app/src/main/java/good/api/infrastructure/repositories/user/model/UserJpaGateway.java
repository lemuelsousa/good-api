package good.api.infrastructure.repositories.user.model;

import good.api.domain.user.User;
import good.api.domain.user.UserGateway;
import good.api.infrastructure.repositories.user.UserJpaRepository;
import good.api.infrastructure.repositories.user.model.mapper.UserToJpaModel;
import good.api.infrastructure.repositories.user.model.mapper.JpaModelToUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserJpaGateway implements UserGateway {

    private final UserJpaRepository userJpaRepository;

    @Autowired
    private UserJpaGateway(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User create(User user) {
        final var save = userJpaRepository.save(UserToJpaModel.convert(user));
        return JpaModelToUser.convert(save);
    }

    @Override
    public User findByEmail(String email) {
        final var userModel = userJpaRepository.findByEmail(email);

        if (userModel == null)
            return null;

        return JpaModelToUser.convert(userModel);
    }

}
