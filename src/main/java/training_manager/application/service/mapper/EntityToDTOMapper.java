package training_manager.application.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface EntityToDTOMapper<O, E> {

    O toDTO(E entity, Object... args);

    E toEntity(O dto, Object... args);

    default List<O> toDTOs(List<E> entities, Object... args) {
        return entities.stream().map(e -> toDTO(e, args)).collect(Collectors.toList());
    }

    default List<E> toEntities(List<O> dtos, Object... args) {
        return dtos.stream().map(dto -> toEntity(dto, args)).collect(Collectors.toList());
    }

}
