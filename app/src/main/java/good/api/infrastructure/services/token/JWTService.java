package good.api.infrastructure.services.token;

import good.api.domain.user.User;
import good.api.infrastructure.services.token.dto.TokenSubjectOutput;
import good.api.infrastructure.services.token.dto.TokenInput;
import good.api.infrastructure.services.token.dto.TokenOutput;

public interface JWTService {

    TokenOutput generateToken(User user);
    TokenSubjectOutput getSubject(TokenInput token);
    boolean validate(TokenInput token);

}
