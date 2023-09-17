package fr.codelines.multidb.repository;

public interface BaseRepositoryInterface<T, ID> {

    T findById(ID id);

    /*
    T save(T entity);

    void delete(T entity);

    void deleteById(ID id);

    Iterable<T> findAll();

    long count();

    boolean existsById(ID id);

    */
}
