package code.with.vanilson.customer.jwt;

import code.with.vanilson.customer.Customer;
import code.with.vanilson.customer.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * JwtService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */
@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.access-token-expiration}")
    private long accessTokenExpire;

    @Value("${application.security.jwt.refresh-token-expiration}")
    private long refreshTokenExpire;



    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        var claims = extractClaims(token);
        return resolver.apply(claims);
    }

    public boolean isValid(String token, UserDetails userDetails) {
        var username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && isExpiredToken(token);
    }

    private boolean isExpiredToken(String token) {
        return !extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Customer customer, long expireTime) {
        return Jwts.builder()
                .subject(customer.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(getSigningKey())
                .compact();

    }

    public boolean isValidRefreshToken(String token, Customer customer) {
        String username = extractUsername(token);

        boolean validRefreshToken = tokenRepository
                .findByRefreshToken(token)
                .map(t -> !t.isLoggedOut())
                .orElse(false);

        return (username.equals(customer.getUsername())) && isExpiredToken(token) && validRefreshToken;
    }

    public String generateAccessToken(Customer customer) {
        return generateToken(customer, accessTokenExpire);
    }

    public String generateRefreshToken(Customer customer) {
        return generateToken(customer, refreshTokenExpire);
    }

    private SecretKey getSigningKey() {
        var keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}