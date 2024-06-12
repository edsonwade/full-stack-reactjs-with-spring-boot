package code.with.vanilson.customer.token;

/**
 * Token
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-12
 */

import code.with.vanilson.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_seq")
    @SequenceGenerator(name = "token_id_seq", sequenceName = "token_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_logged_out")
    private boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}