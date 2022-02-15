package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "training_days")
@Data
@NoArgsConstructor
public class TrainingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int day;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "users_muscles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "muscle_id"))
    private Set<Muscle> muscles;

    @Column(nullable = false)
    private String descriptionExercises;

    private int lastApproaches; //Сколько подходов было выполненов в последний раз

    private int lastRepeats; //Сколько повторений было выполненов в последний раз

    private int lastWeight; //Какой был последний вес

    private LocalDate lastDate = LocalDate.now(); //Когда последний раз делалось

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


}
