package training_manager.demo.service.mapper;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;

@Component
public class NullTrackingMapperDTO {

    public <T> T toEntity(T entity, Object dto) {
        Field[] entityFields = entity.getClass().getDeclaredFields();
        Field[] superclassEntityFields = entity.getClass().getSuperclass().getDeclaredFields();
        Field[] dtoFields = dto.getClass().getDeclaredFields();
        replaceFields(entity, dto, entityFields, dtoFields);
        replaceFields(entity, dto, superclassEntityFields, dtoFields);
        return entity;
    }

    private void replaceFields(Object entity, Object dto, Field[] entityFields, Field[] dtoFields) {
        Arrays.stream(entityFields).forEach(field -> Arrays.stream(dtoFields).forEach(
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
        ));

    }
}
