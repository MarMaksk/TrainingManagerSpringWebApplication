package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
