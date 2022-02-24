package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.MuscleDTO;
import training_manager.demo.entity.Muscle;
import training_manager.demo.enums.MuscleGroupEnum;
import training_manager.demo.exception.no_such.NoSuchMuscleException;
import training_manager.demo.repository.MuscleRepository;
import training_manager.demo.service.mapper.MuscleDTOMapper;

@Service
@RequiredArgsConstructor
public class MuscleService implements CUDService<Muscle, MuscleDTO> {

    private final MuscleRepository repository;

    private final MuscleDTOMapper mapper;

    public MuscleDTO findMuscleByGroup(MuscleGroupEnum muscleGroup) {
        Muscle muscle = repository.findByMuscleGroup(muscleGroup).orElseThrow(() -> new NoSuchMuscleException(
                String.format("No such muscle with muscle group: %s", muscleGroup.name())
        ));
        return mapper.toDTO(muscle);
    }

    @Override
    public MuscleDTO create(Muscle entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public MuscleDTO update(MuscleDTO dto) {
        return mapper.toDTO(repository.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}