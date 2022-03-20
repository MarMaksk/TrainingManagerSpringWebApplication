package training_manager.demo.service.entity;

import training_manager.demo.entity.AbstractEntity;

import javax.transaction.Transactional;

public interface CUDService<E extends AbstractEntity, D> {

    @Transactional
    D create(E entity);

    @Transactional
    D update(D dto);

    @Transactional
    void delete(Long id);
}
