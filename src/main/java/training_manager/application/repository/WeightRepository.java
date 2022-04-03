package training_manager.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.application.entity.Weight;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeightRepository extends JpaRepository<Weight, Long> {

    @Query("from Weight w " +
            "join w.user u " +
            "where u.username = :username")
    List<Weight> findByUsername(@Param("username") String username);

    Optional<Weight> findByUserUsernameAndDate(String username, LocalDate date);

    Optional<Weight> findFirstByUserIdOrderByDateDesc(Long id);

    void deleteByIdAndUserUsername(Long id, String username);
}
