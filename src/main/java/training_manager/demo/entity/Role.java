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
public class Role extends AbstractEntity {

    @Column(nullable = false)
    private RoleEnum role;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinColumn(name = "user_id")
    private Set<User> users = new HashSet<>();
}
