package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.RoleEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private RoleEnum role;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public void addRole(User user) {
        users.add(user);
        user.addRole(this);
    }

    // TODO: 15.02.2022
    public void removeRole(User user) {
        users.add(user);
        user.addRole(this);
    }
}
