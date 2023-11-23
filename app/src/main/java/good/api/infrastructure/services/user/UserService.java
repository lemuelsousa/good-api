package good.api.infrastructure.services.user;

import good.api.domain.user.User;
import good.api.infrastructure.services.user.dto.EmailInput;
import good.api.infrastructure.services.user.dto.UserServiceInput;

public interface UserService {

    User register(UserServiceInput userInput);
    User loadByEmail(EmailInput email);

}
