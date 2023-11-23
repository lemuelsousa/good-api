package good.api.web.security.filter;

import good.api.infrastructure.services.token.dto.TokenInput;
import good.api.infrastructure.services.token.implementation.JWTServiceImpl;
import good.api.infrastructure.services.user.dto.EmailInput;
import good.api.infrastructure.services.user.implementation.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    JWTServiceImpl tokenService;

    @Autowired
    UserServiceImpl userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final var token = recoverToken(request);

        if (token != null) {
            final var emailSubject = tokenService.getSubject
                    (new TokenInput(token));

            final var user = userServiceImpl.loadByEmail
                    (new EmailInput(emailSubject.subject()));

            final var authentication = new UsernamePasswordAuthenticationToken
                    (user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        final var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", "");
    }

}
