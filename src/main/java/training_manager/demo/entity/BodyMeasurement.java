package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "body_masurement")
@NoArgsConstructor
public class BodyMeasurement extends AbstractEntity {

    private LocalDate date;

    private int chest;
    private int waist;
    private int hips;
    private int shoulder;
    private int thigh;
    private int calves;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}
