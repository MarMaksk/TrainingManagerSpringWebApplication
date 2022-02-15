package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import training_manager.demo.entity.BodyMeasurement;

public interface BodyMeasurementRepository extends JpaRepository<BodyMeasurement, Long> {


}
