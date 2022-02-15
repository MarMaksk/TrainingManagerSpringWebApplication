package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.RoleEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class UserInfo {

    @Column(unique = true, nullable = false, updatable = false)
    private String nickname;

    @Column(nullable = false, updatable = false)
    private String password;
}
