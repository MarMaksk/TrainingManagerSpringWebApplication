package training_manager.demo.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import training_manager.demo.enums.TrainingTypeEnum;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractEntity implements UserDetails {

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDate registrationDate;

    private double firstWeight;

    private double height;

    private TrainingTypeEnum trainingType;

    private Long telegramId;

    private boolean deleted;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @PrePersist
    public void prePersist() {
        if (registrationDate == null)
            registrationDate = LocalDate.now();
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + super.getId() + '\'' +
                "username='" + username + '\'' +
                ", registrationDate=" + registrationDate +
                ", firstWeight=" + firstWeight +
                ", height=" + height +
                ", trainingType=" + trainingType +
                ", telegramId=" + telegramId +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> (GrantedAuthority) () -> role.getRole().name()).collect(Collectors.toSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
}
