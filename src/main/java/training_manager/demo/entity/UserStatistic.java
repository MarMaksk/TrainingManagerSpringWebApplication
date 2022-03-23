package training_manager.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users_statistic")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatistic extends AbstractEntity {

    @Column(unique = true)
    private LocalDate date;

    private int weight;

    private int repeats;

    private int approaches;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user = new User();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "muscle_id")
    private Muscle muscle = new Muscle();

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }

}
