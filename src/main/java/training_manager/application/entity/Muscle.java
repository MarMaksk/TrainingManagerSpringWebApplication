package training_manager.application.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.application.enums.MuscleGroupEnum;

import javax.persistence.*;

@Entity
@Data
@Table(name = "muscles")
@NoArgsConstructor
@Builder
public class Muscle extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private MuscleGroupEnum muscleGroup;

    public Muscle(MuscleGroupEnum muscleGroup) {
        this.muscleGroup = muscleGroup;
    }

}
