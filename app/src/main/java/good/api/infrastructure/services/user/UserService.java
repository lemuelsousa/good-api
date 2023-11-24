package good.api.infrastructure.services.user;

import good.api.infrastructure.services.user.dto.UserServiceInput;

public interface UserService {

    void register(UserServiceInput userInput);
}
