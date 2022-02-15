package training_manager.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "users_statistic")
public class UserStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int day;

    private int weight;

    private int repeats;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;

}
