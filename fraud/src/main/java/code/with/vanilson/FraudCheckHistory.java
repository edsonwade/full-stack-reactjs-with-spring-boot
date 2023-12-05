package code.with.vanilson;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
public class FraudCheckHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = 2345564122L;

    @Id
    @SequenceGenerator(
            name = "fraud_id_sequence",
            sequenceName = "fraud_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "fraud_id_sequence")
    @JsonProperty("id")
    private Integer fraudId;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;
}
