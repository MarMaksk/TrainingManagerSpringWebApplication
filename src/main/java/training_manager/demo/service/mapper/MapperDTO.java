package training_manager.demo.service.mapper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class MapperDTO<E, D> {

    public E toEntity(E entity, D dto) {
        Field[] entityFields = entity.getClass().getDeclaredFields();
        Field[] superclassEntityFields = entity.getClass().getSuperclass().getDeclaredFields();
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        replaceFields(entity, dto, entityFields, dtoFields);
        replaceFields(entity, dto, superclassEntityFields, dtoFields);
        return entity;
    }
    // TODO: 24.02.2022 JUST CHECK MAYBE 

    private void replaceFields(E entity, D dto, Field[] entityFields, Field[] dtoFields) {
        Arrays.stream(entityFields).map(field -> {
            Arrays.stream(dtoFields).forEach(
                    dtoF -> {
                        if (field.getName().equals(dtoF.getName())) {
                            try {
                                field.setAccessible(true);
                                dtoF.setAccessible(true);
                                field.set(entity, dtoF.get(dto) == null ? field.get(entity) : dtoF.get(dto));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );
            return 1;
        }).collect(Collectors.toList());
    }
}
