package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.appl.Department;
import ro.teamnet.zth.appl.Location;

import java.util.List;

/**
 * Created by Cosmin.Adamut on 7/14/2017.
 */
public class LocationDao {

    EntityManager entityManager = new EntityManagerImpl();

    public Location findById(long id) {
        return entityManager.findById(Location.class, id);
    }

    public long getNextIdVal(String tableName, String columnIdName) {
        return entityManager.getNextIdVal(tableName, columnIdName);
    }

    public Location insert(Location dep) {
        return (Location) entityManager.insert(dep);
    }

    public List<Location> findAll() {
        return (List<Location>) entityManager.findAll(Location.class);
    }

    public Location update(Location entity) {
        return entityManager.update(entity);
    }


}
