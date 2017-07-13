package ro.teamnet.zth.api.em;

import java.util.List;

/**
 * Created by Cosmin.Adamut on 7/13/2017.
 */
public interface EntityManager {
    public <T> T findById(Class<T> entityClass, Long id);

    public long getNextIdVal(String tableName, String columnIdName);

    public <T> Object insert(T entity);

    public <T> List<T> findAll(Class<T> entityClass);

}
