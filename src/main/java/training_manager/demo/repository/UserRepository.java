package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    List<User> findAllByOrderByIdAsc();

    User findByNickname(String nickname);

    boolean existsByNickname(String nickname);
}
