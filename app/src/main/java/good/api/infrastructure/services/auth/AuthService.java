package good.api.infrastructure.services.auth;

import good.api.domain.user.User;
import good.api.infrastructure.services.auth.dto.LoginInput;

public interface AuthService {

    User validate(LoginInput input);

}
