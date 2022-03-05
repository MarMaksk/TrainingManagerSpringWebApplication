package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "body_masurement")
@NoArgsConstructor
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BodyMeasurement extends AbstractEntity {

    private LocalDate date;

    @Column
    private int chest;
    @Column
    private int waist;
    @Column
    private int hips;
    @Column
    private int shoulder;
    @Column
    private int thigh;
    @Column
    private int calves;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}
