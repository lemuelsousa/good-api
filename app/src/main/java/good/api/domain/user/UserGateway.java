package good.api.domain.user;

public interface UserGateway {
    User create(User user);
    User findByEmail(String email);

}
