package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.TrainingTypeEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.Table;
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
