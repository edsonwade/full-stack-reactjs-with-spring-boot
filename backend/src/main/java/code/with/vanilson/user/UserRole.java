package code.with.vanilson.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@ToString
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private long roleId;
    private String name;

    public UserRole() {
        //default constructors
    }

    public UserRole(long roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }
}
