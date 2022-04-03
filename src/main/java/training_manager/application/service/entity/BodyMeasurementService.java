package training_manager.application.service.entity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training_manager.application.dto.BodyMeasurementDTO;
import training_manager.application.entity.BodyMeasurement;
import training_manager.application.exception.no_such.NoSuchBodyMeasurementException;
import training_manager.application.repository.BodyMeasurementRepository;
import training_manager.application.service.mapper.BodyMeasurementDTOMapper;
import training_manager.application.service.mapper.NullTrackingMapperDTO;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BodyMeasurementService implements CUDService<BodyMeasurement, BodyMeasurementDTO> {

    private final BodyMeasurementRepository repository;
    private final BodyMeasurementDTOMapper mapper;
    private final NullTrackingMapperDTO nullTrackingMapper;


    public List<BodyMeasurementDTO> findAllByUser(String username) {
        return mapper.toDTOs(repository.findAllByUserUsername(username));
    }

    @Transactional
    public BodyMeasurementDTO findBodyMeasurementUser(String username, LocalDate date) {
        BodyMeasurement measurement = repository.findByUserUsernameAndDate(username, date).orElseThrow(() -> new NoSuchBodyMeasurementException(
                String.format("No such body measurement with user id: %s and date: %s", username, date.toString())
        ));
        return mapper.toDTO(measurement);
    }

    public BodyMeasurementDTO createFromDTO(BodyMeasurementDTO dto) {
        BodyMeasurement entity = mapper.toEntity(dto);
        try {
            BodyMeasurement oldBody = repository.
                    findFirstByUserUsernameOrderByDateDesc(dto.getUsername()).orElseThrow(NoSuchBodyMeasurementException::new);
            entity.setCalves(entity.getCalves() == 0 ? oldBody.getCalves() : entity.getCalves());
            entity.setChest(entity.getChest() == 0 ? oldBody.getChest() : entity.getChest());
            entity.setHips(entity.getHips() == 0 ? oldBody.getHips() : entity.getHips());
            entity.setShoulder(entity.getShoulder() == 0 ? oldBody.getShoulder() : entity.getShoulder());
            entity.setThigh(entity.getThigh() == 0 ? oldBody.getThigh() : entity.getThigh());
            entity.setWaist(entity.getWaist() == 0 ? oldBody.getWaist() : entity.getWaist());
        } catch (NoSuchBodyMeasurementException ex) {
            log.warn(ex.getLocalizedMessage());
        }
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public BodyMeasurementDTO create(BodyMeasurement entity) {
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public BodyMeasurementDTO update(BodyMeasurementDTO dto) {
        BodyMeasurement entity = repository.findByUserUsernameAndDate(dto.getUsername(), dto.getDate()).orElseGet(BodyMeasurement::new);
        nullTrackingMapper.toEntity(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(Long id, String username) {
        repository.deleteByIdAndUserUsername(id, username);
    }
}
