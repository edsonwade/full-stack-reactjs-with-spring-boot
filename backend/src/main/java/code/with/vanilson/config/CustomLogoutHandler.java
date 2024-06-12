package code.with.vanilson.config;

import code.with.vanilson.customer.exception.TokenNotFoundException;
import code.with.vanilson.customer.repository.TokenRepository;
import code.with.vanilson.customer.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

/**
 * CustomLogoutHandler
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Configuration
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    public CustomLogoutHandler(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request,
                       HttpServletResponse response,
                       Authentication authentication) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        String token = authHeader.substring(7);
        Token storedToken = tokenRepository.findByAccessToken(token).
                orElseThrow(() -> new TokenNotFoundException("token not founded"));

        if (storedToken != null) {
            storedToken.setLoggedOut(true);
            tokenRepository.save(storedToken);
        }
    }
}