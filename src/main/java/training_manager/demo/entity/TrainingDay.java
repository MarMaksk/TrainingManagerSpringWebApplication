package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroup;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

@Entity
@Table(name = "training_days")
@Data
@NoArgsConstructor
public class TrainingDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeak;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MuscleGroup muscleGroup;

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

    private int countForAllTime;

    private int weightForAllTime;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

    public TrainingDay(DayOfWeek dayOfWeak, MuscleGroup muscleGroup, String description, int sequenceNumber, int approaches, int repeats, int weightUsually) {
        this.dayOfWeak = dayOfWeak;
        this.muscleGroup = muscleGroup;
        this.description = description;
        this.sequenceNumber = sequenceNumber;
        this.approaches = approaches;
        this.repeats = repeats;
        this.weightUsually = weightUsually;
    }

}
