package code.with.vanilson.customer.service;

import code.with.vanilson.customer.Customer;
import code.with.vanilson.customer.repository.CustomerRepository;
import code.with.vanilson.customer.authenticatinResponse.AuthenticationResponse;
import code.with.vanilson.customer.jwt.JwtService;
import code.with.vanilson.customer.repository.TokenRepository;
import code.with.vanilson.customer.token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AuthenticationService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(Customer request) {

        // check if user already exist. if exist than authenticate the user
        if (customerRepository.findCustomerByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, null, "User already exist");
        }

        var customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        customer.setRole(request.getRole());

        customer = customerRepository.save(customer);

        String accessToken = jwtService.generateAccessToken(customer);
        String refreshToken = jwtService.generateRefreshToken(customer);

        saveUserToken(accessToken, refreshToken, customer);

        return new AuthenticationResponse(accessToken, refreshToken, "User registration was successful");

    }

    public AuthenticationResponse authenticate(Customer request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var customer = customerRepository.findCustomerByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        String accessToken = jwtService.generateAccessToken(customer);
        String refreshToken = jwtService.generateRefreshToken(customer);

        revokeAllTokenByUser(customer);
        saveUserToken(accessToken, refreshToken, customer);

        return new AuthenticationResponse(accessToken, refreshToken, "Customer login was successful");
    }

    private void revokeAllTokenByUser(Customer customer) {
        List<Token> validTokens = tokenRepository.findAllAccessTokensByUser(customer.getId().intValue());
        if (validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t -> {
            t.setLoggedOut(true);
        });

        tokenRepository.saveAll(validTokens);
    }

    private void saveUserToken(String accessToken, String refreshToken, Customer customer) {
        Token token = new Token();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setLoggedOut(false);
        token.setCustomer(customer);
        tokenRepository.save(token);
    }

    public ResponseEntity refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) {
        // extract the token from authorization header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }

        String token = authHeader.substring(7);

        // extract username from token
        String username = jwtService.extractUsername(token);

        // check if the user exist in database
        var customer = customerRepository.findCustomerByUsername(username)
                .orElseThrow(() -> new RuntimeException("No user found"));

        // check if the token is valid
        if (jwtService.isValidRefreshToken(token, customer)) {
            // generate access token
            String accessToken = jwtService.generateAccessToken(customer);
            String refreshToken = jwtService.generateRefreshToken(customer);

            revokeAllTokenByUser(customer);
            saveUserToken(accessToken, refreshToken, customer);

            return new ResponseEntity(new AuthenticationResponse(accessToken, refreshToken, "New token generated"),
                    HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);

    }
}