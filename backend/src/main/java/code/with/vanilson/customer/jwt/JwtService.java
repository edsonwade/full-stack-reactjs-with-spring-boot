package code.with.vanilson.customer.jwt;

import code.with.vanilson.customer.Customer;
import code.with.vanilson.customer.CustomerDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${secret.key}")
    String token;
    private final String SECRET_KEY = token;

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        var claims = extractClaims(token);
        return resolver.apply(claims);
    }

    public boolean isValid(String token, Customer customer) {
        var username = extractUsername(token);
        return (username.equals(customer.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
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

    public String generateToken(Customer customer) {
        return Jwts.builder()
                .subject(customer.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();

    }

    private SecretKey getSigningKey() {
        var keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}