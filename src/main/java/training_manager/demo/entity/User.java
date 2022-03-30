package training_manager.demo.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import training_manager.demo.enums.TrainingTypeEnum;
import training_manager.demo.repository.RoleRepository;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends AbstractEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDate registrationDate;

    private int firstWeight;

    private int height;

    private TrainingTypeEnum trainingType;

    private Long telegramId;

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
 }
