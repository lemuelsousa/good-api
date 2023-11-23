package good.api.infrastructure.services.auth.implementation;

import good.api.domain.user.User;
import good.api.infrastructure.repositories.user.model.UserJpaGateway;
import good.api.infrastructure.services.auth.AuthService;
import good.api.infrastructure.services.auth.dto.LoginInput;
import good.api.infrastructure.services.auth.exception.AuthException;
import good.api.infrastructure.services.auth.exception.AuthServiceException;
import good.api.utils.BCryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserJpaGateway userJpaGateway;

    @Override
    public User validate(LoginInput input) {
        final var user = userJpaGateway.findByEmail(input.email());

        if (user == null)
            throw new AuthServiceException("Account not exists");


        if (!BCryptUtils.bcrypt().matches(input.password(), user.password()))
            throw new AuthServiceException("Incorrect password");

        return user;
    }
}