package good.api.infrastructure.repositories.user.model;

import good.api.utils.BCryptUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
public class UserJpaModel {

    @Id
    @Column(nullable = false, unique = true)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String userRole;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    public UserJpaModel() {
    }

    private UserJpaModel(UUID id, String name, String email, String password, String userRole, Instant createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userRole = userRole.toUpperCase();
        this.createdAt = createdAt;
    }

    public static UserJpaModel build(UUID id, String name, String email, String password, String userRole) {
        return new UserJpaModel(id, name, email, BCryptUtils.bcrypt().encode(password), userRole, Instant.now());
    }

    public static UserJpaModel with(UUID id, String name, String email, String password, String userRole, Instant createdAt) {
        return new UserJpaModel(id, name, email, password, userRole, createdAt);
    }

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    public String userRole() {
        return userRole;
    }

    public Instant createdAt() {
        return createdAt;
    }
}
