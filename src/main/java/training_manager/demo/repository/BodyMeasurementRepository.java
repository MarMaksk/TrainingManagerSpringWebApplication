package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.BodyMeasurement;

@Repository
public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {


}
