package training_manager.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training_manager.demo.entity.UserStatistic;
import training_manager.demo.enums.MuscleGroupEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserStatisticRepository extends JpaRepository<UserStatistic, Long> {

    @Override
    List<UserStatistic> findAll();

    @Query("from UserStatistic us " +
            "join us.user user " +
            "where user.id = :id")
    List<UserStatistic> findAllByUserId(@Param("id") Long userId);

    @Query("from UserStatistic us " +
            "join us.user user " +
            "where user.id = :id and us.date = :date " +
            "order by us.date")
    List<UserStatistic> findAllByUserIdAndDate(@Param("id") Long userId,
                                               @Param("date") LocalDate date);

    @Query("from UserStatistic us " +
            "join us.user user " +
            "join us.muscle mus " +
            "where user.id = :id and mus.muscle = :muscleGroup and us.date = :date")
    Optional<UserStatistic> findByUserIdAndDataAndMuscleGroup(@Param("id") Long userId,
                                                              @Param("muscleGroup") MuscleGroupEnum muscleGroup,
                                                              @Param("date") LocalDate date);
}
