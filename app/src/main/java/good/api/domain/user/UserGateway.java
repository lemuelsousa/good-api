package good.api.domain.user;

public interface UserGateway {
    void create(User user);
    User findByEmail(String email);

}
