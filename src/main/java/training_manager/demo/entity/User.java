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
    private String nickname;

    @Column(nullable = false)
    private String password;

    private LocalDate registrationDate;

    private int firstWeight;

    private int height;

    private TrainingTypeEnum trainingType;

    private Long telegramId;

    public User(String nickname, String password) {
        this.nickname = nickname;
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
                "nickname='" + nickname + '\'' +
                ", registrationDate=" + registrationDate +
                ", firstWeight=" + firstWeight +
                ", height=" + height +
                ", trainingType=" + trainingType +
                ", telegramId=" + telegramId +
                '}';
    }
 }
