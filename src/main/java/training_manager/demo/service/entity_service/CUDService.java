package training_manager.demo.service.entity_service;

public interface CUDService<E, D> {
    D create(E entity);

    D update(D dto);

    void delete(Long id);
}
