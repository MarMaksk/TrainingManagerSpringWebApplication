package training_manager.application.service.entity;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.application.dto.MuscleDTO;
import training_manager.application.entity.Muscle;
import training_manager.application.enums.MuscleGroupEnum;
import training_manager.application.exception.no_such.NoSuchMuscleException;
import training_manager.application.repository.MuscleRepository;
import training_manager.application.service.mapper.MuscleDTOMapper;
import training_manager.application.service.mapper.NullTrackingMapperDTO;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MuscleService implements CUDService<Muscle, MuscleDTO> {

    private final MuscleRepository repository;
    private final MuscleDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;

    @Transactional
    public MuscleDTO findMuscleByGroup(MuscleGroupEnum muscleGroup) {
        Muscle muscle = repository.findByMuscleGroup(muscleGroup)
                .orElseThrow(() -> new NoSuchMuscleException(
                        String.format("No such muscle with muscle group: %s", muscleGroup.name())
                ));
        return mapper.toDTO(muscle);
    }

    public List<MuscleDTO> findAllMuscle(){
        return mapper.toDTOs(repository.findAll());
    }

    @Override
    public MuscleDTO create(Muscle entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public MuscleDTO update(MuscleDTO dto) {
        Muscle muscle = repository.findByMuscleGroup(dto.getMuscleGroup()).orElseThrow(NoSuchMuscleException::new);
        nullTrackingMapper.toEntity(muscle, dto);
        return mapper.toDTO(repository.save(muscle));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
