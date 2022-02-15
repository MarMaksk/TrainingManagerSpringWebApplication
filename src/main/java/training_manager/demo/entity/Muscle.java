
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
public class Muscle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private MuscleGroupEnum muscleGroup;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "muscles")
    private Set<TrainingDay> trainingDay = new HashSet<>();

}
