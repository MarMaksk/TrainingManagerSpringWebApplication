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
    private String dayOfWeak;

    @Column(nullable = false)
    private String muscleGroup;

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

    @Temporal(TemporalType.DATE)
    private Date localDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

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
        this.dayOfWeak = dayOfWeak.getDisplayName(TextStyle.FULL, Locale.getDefault());
        this.muscleGroup = muscleGroup.name();
        this.description = description;
        this.sequenceNumber = sequenceNumber;
        this.approaches = approaches;
        this.repeats = repeats;
        this.weightUsually = weightUsually;
    }

    public DayOfWeek getDayOfWeak() {
        return DayOfWeek.valueOf(dayOfWeak);
    }

    public void setDayOfWeak(DayOfWeek dayOfWeak) {
        this.dayOfWeak = dayOfWeak.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public MuscleGroup getMuscleGroup() {
        return MuscleGroup.valueOf(muscleGroup);
    }

    public void setMuscleGroup(MuscleGroup muscleGroup) {
        this.muscleGroup = muscleGroup.name();
    }
}
