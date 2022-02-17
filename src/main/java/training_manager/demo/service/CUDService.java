package training_manager.demo.service;

public interface CUDService<T> {
    T create(T entity);

    T update(T entity);

    void delete(T entity);
}
