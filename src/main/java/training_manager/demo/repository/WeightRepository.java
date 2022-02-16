package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    Weight findByUser(User user);

}
