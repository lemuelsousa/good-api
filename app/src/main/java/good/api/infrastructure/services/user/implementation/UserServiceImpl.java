package good.api.infrastructure.services.user.implementation;

import good.api.domain.user.User;
import good.api.infrastructure.repositories.user.model.UserJpaGateway;
import good.api.infrastructure.services.user.UserService;
import good.api.infrastructure.services.user.dto.EmailInput;
import good.api.infrastructure.services.user.dto.UserServiceInput;
import good.api.infrastructure.services.user.dto.mapper.UserServiceInputToUser;
import good.api.infrastructure.services.user.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserJpaGateway userJpaGateway;

    @Override
    public User register(UserServiceInput input) {
        final var result = userJpaGateway.findByEmail(input.email());

        if (result != null)
            throw new UserServiceException("Email is already being used");

        final var user = UserServiceInputToUser.convert(input);
        userJpaGateway.create(user);

        return user;
    }

    @Override
    public User loadByEmail(EmailInput email) {
        final var result = userJpaGateway.findByEmail(email.email());

        if (result == null)
            throw new UserServiceException("Email not exists");

        return result;
    }


}
