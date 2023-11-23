package good.api.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptUtils {

    public static PasswordEncoder bcrypt() {
        return new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return bcrypt().encode(password);
    }

    public boolean verify( String encodedPassword, String passwordRaw) {
        return bcrypt().matches(encodedPassword, encodedPassword);
    }
}
