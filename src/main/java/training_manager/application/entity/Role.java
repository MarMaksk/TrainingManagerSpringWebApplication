package training_manager.application.entity;

import lombok.*;
import training_manager.application.enums.RoleEnum;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "user_id")
    private Set<User> users = new HashSet<>();

    public Role(RoleEnum role) {
        this.role = role;
    }

    public void addUserToRole(User user){
        users.add(user);
    }
}
