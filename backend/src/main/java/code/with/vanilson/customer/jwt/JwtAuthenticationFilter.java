package code.with.vanilson.customer.jwt;

import code.with.vanilson.customer.service.CustomerDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomerDetails customerDetails;

    public JwtAuthenticationFilter(JwtService jwtService, CustomerDetails customerDetails) {
        this.jwtService = jwtService;
        this.customerDetails = customerDetails;

    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

        } catch (NullPointerException exception) {
            exception.getMessage();
        }

        var token = authHeader.substring(7);
        var username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var customerDetail = customerDetails.loadUserByUsername(username);
            if (jwtService.isValid(token, customerDetail)) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(
                        customerDetail,
                        null,
                        customerDetail.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
        filterChain.doFilter(request, response);

    }
}