package training_manager.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import training_manager.demo.enums.MuscleGroupEnum;

import javax.persistence.*;

@Entity
@Data
@Table(name = "muscles")
@NoArgsConstructor
@Builder
public class Muscle extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private MuscleGroupEnum muscle;

    public Muscle(MuscleGroupEnum muscle) {
        this.muscle = muscle;
    }

}
