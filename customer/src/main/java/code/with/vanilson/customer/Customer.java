package code.with.vanilson.customer;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 2345564122L;

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence")
    private long id;
    private String firstName;
    private String lastName;
    private String email;

}