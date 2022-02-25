package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.RoleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class Role extends AbstractEntity {

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

    public void removeRole(User user) {
        users.remove(user);
        user.removeRole(this);
    }
}
