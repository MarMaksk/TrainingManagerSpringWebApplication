
package training_manager.demo.entity;

import lombok.*;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "muscles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Muscle extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private MuscleGroupEnum muscleGroup;

    @OneToMany(mappedBy = "muscle", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TrainingDay> trainingDay = new HashSet<>();

    @OneToMany(mappedBy = "muscle", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<UserStatistic> userStatistics = new HashSet<>();

    public Muscle(MuscleGroupEnum muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

    public void addTrainingDay(TrainingDay trainingDay) {
        this.trainingDay.add(trainingDay);
        trainingDay.setMuscle(this);
    }

    public void addUserStatistics(UserStatistic userStatistics) {
        this.userStatistics.add(userStatistics);
        userStatistics.setMuscle(this);
    }
}
