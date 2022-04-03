package training_manager.application.entity;

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

    @Column(unique = true)
    private LocalDate date;

    @Column(nullable = false)
    private double weight;

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
