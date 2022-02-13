package training_manager.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.Role;

import javax.persistence.*;

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

    @Column(nullable = false)
    private Role role;
}
