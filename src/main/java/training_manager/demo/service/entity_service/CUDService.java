package training_manager.demo.service.entity_service;

import javax.transaction.Transactional;

public interface CUDService<E, D> {

    @Transactional
    D create(E entity);

    @Transactional
    D update(D dto);

    @Transactional
    void delete(Long id);
}
