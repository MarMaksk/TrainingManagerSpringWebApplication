package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.Weight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    @Query("from Weight w " +
            "join w.user u " +
            "where u.id = :id")
    List<Weight> findByUserId(@Param("id") Long userId);

    Optional<Weight> findByUserIdAndDate(Long userId, LocalDate date);

    Optional<Weight> findFirstByUserIdOrderByDateDesc(Long id);

}
