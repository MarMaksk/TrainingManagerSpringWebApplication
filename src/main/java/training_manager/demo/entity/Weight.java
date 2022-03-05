package training_manager.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "person_weigth")
@Data
@NoArgsConstructor
public class Weight extends AbstractEntity {

    private LocalDate date;

    @Column(nullable = false)
    private int weight;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    public Weight(int weight, User user) {
        this.weight = weight;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        if (date == null) {
            date = LocalDate.now();
        }
    }
}
