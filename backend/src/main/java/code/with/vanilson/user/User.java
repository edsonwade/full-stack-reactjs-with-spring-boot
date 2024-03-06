package code.with.vanilson.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "tb_users")
@JsonPropertyOrder({"user_id", "username", "password", "cohortStartDate"})
@EqualsAndHashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long userId;
    private String username;
    @JsonIgnore
    private String password;
    @Column(name = "createdAt")
    private LocalDate cohortStartDate;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();

    public User() {
        //default constructors
    }

    public User(Long userId, String username, String password, LocalDate cohortStartDate) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.cohortStartDate = cohortStartDate;

    }

    public User(Long userId, String username, String password, LocalDate cohortStartDate, Set<UserRole> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.cohortStartDate = cohortStartDate;
        this.roles = roles;
    }

}
