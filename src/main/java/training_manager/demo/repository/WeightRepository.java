package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.User;
import training_manager.demo.entity.Weight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    List<Weight> findAllByUser(User user);

    Optional<Weight> findByUserAndDate(User user, LocalDate date);

    Optional<Weight> findFirstByUserIdOrderByDateDesc(Long id);

}
