package good.api.domain.user;

import good.api.domain.user.role.UserRole;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class User implements UserDetails {

    private final UUID id;
    private String name;
    private String email;
    private String password;
    private UserRole userRole;

    private User(UUID id, String name, String email, String password, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
        this.userRole = role;
    }

    public static User build(String name, String email, String password) {
        return new User(UUID.randomUUID(), name, email, password, UserRole.USER);
    }

    public static User with(UUID id, String name, String email, String password, UserRole role) {
        return new User(id, name, email, password, role);
    }

    // TODO: add validations

    public UUID id() {
        return id;
    }

    public String name() {
        return name;
    }


    public String email() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole userRole() {
        return userRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole == UserRole.ADMIN)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
