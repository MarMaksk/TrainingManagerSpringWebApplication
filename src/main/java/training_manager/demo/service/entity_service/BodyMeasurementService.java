package training_manager.demo.service.entity_service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import training_manager.demo.dto.BodyMeasurementDTO;
import training_manager.demo.entity.BodyMeasurement;
import training_manager.demo.exception.no_such.NoSuchBodyMeasurementException;
import training_manager.demo.repository.BodyMeasurementRepository;
import training_manager.demo.service.mapper.BodyMeasurementDTOMapper;
import training_manager.demo.service.mapper.NullTrackingMapperDTO;

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


    public List<BodyMeasurementDTO> findAllByUser(Long id) {
        return mapper.toDTOs(repository.findAllByUserId(id));
    }

    @Transactional
    public BodyMeasurementDTO findBodyMeasurementUser(Long id, LocalDate date) {
        BodyMeasurement measurement = repository.findByUserIdAndDate(id, date).orElseThrow(() -> new NoSuchBodyMeasurementException(
                String.format("No such body measurement with user id: %d and date: %s", id, date.toString())
        ));
        return mapper.toDTO(measurement);
    }

    public BodyMeasurementDTO createFromDTO(BodyMeasurementDTO dto) {
        BodyMeasurement entity = mapper.toEntity(dto);
        try {
            BodyMeasurement oldBody = repository.
                    findFirstByUserIdOrderByDateDesc(dto.getUserId()).orElseThrow(NoSuchBodyMeasurementException::new);
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
        BodyMeasurement entity = repository.findByUserIdAndDate(dto.getUserId(), dto.getDate()).orElseGet(BodyMeasurement::new);
        nullTrackingMapper.toEntity(entity, dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
