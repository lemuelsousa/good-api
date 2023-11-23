package good.api.domain.user.role;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.List;

public enum UserRole {

    USER("user"),
    ADMIN("admin");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
