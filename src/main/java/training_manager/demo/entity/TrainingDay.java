package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "training_days")
@Data
@NoArgsConstructor
public class TrainingDay extends AbstractEntity {

    @Column(nullable = false)
    private int day;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "muscle_id")
    private Muscle muscle;

    @Column(nullable = false)
    private String descriptionExercises;

    private int lastApproaches; //Сколько подходов было выполненов в последний раз

    private int lastRepeats; //Сколько повторений было выполненов в последний раз

    private int lastWeight; //Какой был последний вес

    private LocalDate lastDate; //Когда последний раз делалось

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        if (lastDate == null) {
            lastDate = LocalDate.now();
        }
    }

    public void addMuscle(Muscle muscle) {
        this.muscle = muscle;
    }
}
