package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.BodyMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {

    List<BodyMeasurement> findAllByUserUsername(String username);

    Optional<BodyMeasurement> findByUserUsernameAndDate(String username, LocalDate date);

    Optional<BodyMeasurement> findFirstByUserUsernameOrderByDateDesc(String username);


    void deleteByIdAndUserUsername(Long id, String username);
}
