package training_manager.demo.entity;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import training_manager.demo.enums.TrainingTypeEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    private int firstWeight;

    private int height;

    private TrainingTypeEnum trainingType;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "users_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<Weight> weights = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @BatchSize(size = 7)
    private Set<TrainingDay> trainingDays = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<BodyMeasurement> bodyMeasurements = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private Set<UserStatistic> userStatistics = new HashSet<>();

    private Long telegramId;

    public User(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    protected void addRole(Role role) {
        roles.add(role);
    }

    protected void removeRole(Role role) {
        roles.remove(role);
    }

    public void addWeight(Weight weight) {
        this.weights.add(weight);
        weight.setUser(this);
    }

    public void removeWeight(Weight weight) {
        this.weights.remove(weight);
        weight.setUser(null);
    }

    public void addTrainingDay(TrainingDay trainingDay) {
        this.trainingDays.add(trainingDay);
        trainingDay.setUser(this);
    }

    public void removeTrainingDay(TrainingDay trainingDay) {
        this.trainingDays.remove(trainingDay);
        trainingDay.setUser(null);
    }

    public void addBodyMeasurement(BodyMeasurement bodyMeasurement) {
        this.bodyMeasurements.add(bodyMeasurement);
        bodyMeasurement.setUser(this);
    }

    public void removeBodyMeasurement(BodyMeasurement bodyMeasurement) {
        this.bodyMeasurements.remove(bodyMeasurement);
        bodyMeasurement.setUser(null);
    }
}
