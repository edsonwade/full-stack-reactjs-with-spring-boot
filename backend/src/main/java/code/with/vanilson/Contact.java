package code.with.vanilson;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

/**
 * Contact
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-06-11
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
@Table(name = "contacts")
public class Contact {
    @Id
    @UuidGenerator
    @Column(name = "contact_id", unique = true, updatable = false)
    private String contactId;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String title;
    private String phone;
    private String address;
    private String status;
    private String photoUrl;
}