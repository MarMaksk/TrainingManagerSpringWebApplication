package training_manager.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
//@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UserInfo userInfo;

    private int height;

    private String trainingType;

    private int starting_weight;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<Weight> weights = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<TrainingDay> trainingDays = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    //@Builder.Default
    private Set<BodyMeasurement> bodyMeasurements = new HashSet<>();

    private Long telegramId;

    public User(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void addWeight(Weight weight) {
        this.weights.add(weight);
        weight.setUser(this);
    }

    public void addTrainingDay(TrainingDay trainingDay) {
        this.trainingDays.add(trainingDay);
        trainingDay.setUser(this);
    }

    public void addBodyMeasurement(BodyMeasurement bodyMeasurement) {
        this.bodyMeasurements.add(bodyMeasurement);
        bodyMeasurement.setUser(this);
    }

    public void removeWeight(Weight weight) {
        this.weights.remove(weight);
        weight.setUser(null);
    }

    public void removeTrainingDay(TrainingDay trainingDay) {
        this.trainingDays.remove(trainingDay);
        trainingDay.setUser(null);
    }

    public void removeBodyMeasurement(BodyMeasurement bodyMeasurement) {
        this.bodyMeasurements.remove(bodyMeasurement);
        bodyMeasurement.setUser(null);
    }
}
