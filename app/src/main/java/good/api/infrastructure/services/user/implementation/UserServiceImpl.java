package good.api.infrastructure.services.user.implementation;

import good.api.infrastructure.repositories.user.model.UserJpaGateway;
import good.api.infrastructure.services.user.UserService;
import good.api.infrastructure.services.user.dto.UserServiceInput;
import good.api.infrastructure.services.user.dto.mapper.UserServiceInputToUser;
import good.api.infrastructure.services.user.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserJpaGateway userJpaGateway;

    @Override
    public void register(UserServiceInput input) {
        final var result = userJpaGateway.findByEmail(input.email());

        if (result != null)
            throw new UserServiceException("Email is already being used");

        final var user = UserServiceInputToUser.convert(input);
        userJpaGateway.create(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaGateway.findByEmail(username);
    }
}
