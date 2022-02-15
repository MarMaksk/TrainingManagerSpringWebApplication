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
    private String description;

    @Column(nullable = false)
    private int sequenceNumber;

    @Column(nullable = false)
    private int approaches;

    @Column(nullable = false)
    private int repeats;

    @Column(nullable = false)
    private int weightUsually;

    private LocalDate date = LocalDate.now();

    private int count_today;

    private int weightToday;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public TrainingDay(DayOfWeek dayOfWeak, MuscleGroupEnum muscleGroup, String description, int sequenceNumber, int approaches, int repeats, int weightUsually){
        this.description = description;
        this.sequenceNumber = sequenceNumber;
        this.approaches = approaches;
        this.repeats = repeats;
        this.weightUsually = weightUsually;
    }

}
