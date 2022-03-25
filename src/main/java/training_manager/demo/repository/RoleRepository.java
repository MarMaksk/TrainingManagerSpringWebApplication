package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.Role;
import training_manager.demo.enums.RoleEnum;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRole(RoleEnum roleEnum);

    @Query("FROM Role r " +
            "join r.users u " +
            "where u.id = :id")
    Set<Role> findByUserId(@Param("id") Long userId);
}
