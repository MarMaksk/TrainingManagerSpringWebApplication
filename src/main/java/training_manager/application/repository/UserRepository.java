package training_manager.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.application.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByOrderByIdAsc();

    Optional<User> findByUsername(String username);

    Optional<User> findByIdAndUsername(Long id, String username);

    boolean existsByUsername(String username);

    void deleteByIdAndUsername(Long id, String username);

    Optional<User> findByTelegramId(Long id);
}
