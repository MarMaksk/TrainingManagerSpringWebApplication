package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;

public interface WeightRepository extends JpaRepository<Weight, Long> {

    Weight findByUser(User user);

}
