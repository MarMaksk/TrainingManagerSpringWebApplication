package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "person_weigth")
@Data
@NoArgsConstructor
public class Weight extends AbstractEntity {

    private LocalDate date = LocalDate.now();

    @Column(nullable = false)
    private int weight;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
