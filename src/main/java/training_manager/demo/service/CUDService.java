package training_manager.demo.service;

public interface CUDService<E, D> {
    D create(E entity);

    D update(D entity);

    void delete(D entity);
}
