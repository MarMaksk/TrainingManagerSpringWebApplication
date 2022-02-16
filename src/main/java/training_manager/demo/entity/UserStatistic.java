package training_manager.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Table(name = "users_statistic")
public class UserStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data = LocalDate.now();

    private int weight;

    private int repeats;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @Column(nullable = false)
//    @ManyToMany
//    @JoinTable(name = "users_muscles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "muscle_id"))
//    private Set<Muscle> muscles;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "muscle_id")
    private Muscle muscle;

}
