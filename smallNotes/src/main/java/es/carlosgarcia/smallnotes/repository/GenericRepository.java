package es.carlosgarcia.smallnotes.repository;

import java.io.Serializable;
import java.util.List;

/**
 * Repository for Notes Impl
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public interface GenericRepository <T> {
    public List<T> getAll(Class<T> typeClass);
    public T findByPK(Class<T> typeClass, Serializable id);
    public void saveOrUpdate(T object);
    public void delete(T object);
}
