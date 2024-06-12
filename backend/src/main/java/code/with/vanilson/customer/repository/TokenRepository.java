package code.with.vanilson.customer.repository;

/**
 * TokenRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */

import code.with.vanilson.customer.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
            select t from Token t inner join Customer u on t.customer.id = u.id
            where t.customer.id = :customerId and t.loggedOut = false
            """)
    List<Token> findAllAccessTokensByUser(@Param("customerId") Integer userId);

    Optional<Token> findByAccessToken(String token);

    Optional<Token> findByRefreshToken(String token);
}