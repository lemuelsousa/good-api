package good.api.infrastructure.repositories.user.excepion;

public class UserPersistenceException extends RuntimeException {

    public UserPersistenceException(String message) {
        super(message);
    }

}
