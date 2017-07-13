package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin.Adamut on 7/13/2017.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, Long id) {

        Connection conn = DBManager.getConnection();
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
        Field idField = EntityUtils.getFieldsByAnnotations(entityClass, Id.class).get(0);
        //String name= idField.getAnnotation(Id.class).name();
        List<Field> columnField = EntityUtils.getFieldsByAnnotations(entityClass, Column.class);
        Condition condition = new Condition();
        condition.setValue(id);
        condition.setColumnName(idField.getAnnotation(Id.class).name());

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addCondition(condition);

        String query = queryBuilder.createQuery();

        Statement stm = null;
        ResultSet res = null;
        T newInstance = null;
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            while (res.next()) {
                newInstance = entityClass.newInstance();
                for (ColumnInfo currentColumn : columnList) {
                    Field currentField = newInstance.getClass().getDeclaredField(currentColumn.getColumnName());
                    currentField.setAccessible(true);
                    currentField.set(newInstance,EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()),currentField.getType()));
                    //  currentColumn.getColumnName();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return newInstance;
    }

    @Override
    public long getNextIdVal(String tableName, String columnIdName) {
        Connection conn = DBManager.getConnection();
        PreparedStatement stm = null;
        ResultSet res = null;
        long id = -1;

        try {
            String query = "SELECT MAX(" + columnIdName + ") FROM " + tableName;
            stm = conn.prepareStatement(query);
            res = stm.executeQuery();
            while (res.next()) {
                id = res.getLong(1) + 1;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    @Override
    public <T> Object insert(T entity) {
        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
        Long nextIdValue = Long.valueOf(-1);
        for (ColumnInfo currentColumnInfo : columnList) {
            if (currentColumnInfo.isId()) {
                nextIdValue = getNextIdVal(tableName, currentColumnInfo.getDbColumnName());
                currentColumnInfo.setValue(nextIdValue);
            } else {
                try {
                    Field declaredField = entity.getClass().getDeclaredField(currentColumnInfo.getColumnName());
                    declaredField.setAccessible(true);

                    Object value = declaredField.get(entity);
                    currentColumnInfo.setValue(value);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.INSERT);
        String query = queryBuilder.createQuery();
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            return findById(entity.getClass(), nextIdValue);


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.SELECT);
        String query = queryBuilder.createQuery();
        List<T> list = new ArrayList<T>();
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            while (res.next()) {
                T instance = entityClass.newInstance();
                instance = entityClass.newInstance();
                for (ColumnInfo currentColumn : columnList) {
                    Field field = instance.getClass().getDeclaredField(currentColumn.getColumnName());
                    field.setAccessible(true);
                    field.set(instance, EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()),field.getType()));
                }
                list.add(instance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return list;
    }

}
