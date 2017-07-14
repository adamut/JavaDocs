package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import javax.xml.transform.Result;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
                    currentField.set(newInstance, EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()), currentField.getType()));
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
        Long nextIdValue = (long) -1;
        nextIdValue = getaLong(entity, tableName, columnList, nextIdValue);

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

    private <T> Long getaLong(T entity, String tableName, List<ColumnInfo> columnList, Long nextIdValue) {
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

                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
        return nextIdValue;
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
                    field.set(instance, EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()), field.getType()));
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

    @Override
    public <T> T update(T entity) {
        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
        iterateColumns(entity, columnList);
        Condition condition = new Condition();
        condition.setValue(columnList.get(0).getValue());
        condition.setColumnName(columnList.get(0).getDbColumnName());
        Field idField = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class).get(0);

        ColumnInfo idColumn = removeIdColumnFromUpdateFields(columnList);

        QueryBuilder queryBuilder = new QueryBuilder();

        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.UPDATE);
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            return (T) findById(entity.getClass(), (Long) idColumn.getValue());

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

    private ColumnInfo removeIdColumnFromUpdateFields(List<ColumnInfo> columnList) {
        for (ColumnInfo columnInfo : columnList) {
            if (columnInfo.getColumnName().equals("id")) {
                columnList.remove(columnInfo);
                return columnInfo;
            }
        }
        return null;
    }

    @Override
    public void delete(Object entity) {
        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
        iterateColumns(entity, columnList);


        Condition condition = new Condition();
        condition.setValue(columnList.get(0).getValue());
        condition.setColumnName(columnList.get(0).getDbColumnName());
        Field idField = EntityUtils.getFieldsByAnnotations(entity.getClass(), Id.class).get(0);

        ColumnInfo idColumn = removeIdColumnFromUpdateFields(columnList);

        QueryBuilder queryBuilder = new QueryBuilder();

        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.DELETE);
        queryBuilder.addCondition(condition);
        String query = queryBuilder.createQuery();


        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void iterateColumns(Object entity, List<ColumnInfo> columnList) {
        for (ColumnInfo currentColumn : columnList) {
            try {
                Class currentClass = entity.getClass();
                Field currentField = currentClass.getDeclaredField(currentColumn.getColumnName());
                currentField.setAccessible(true);
                Object value = currentField.get(entity);
                currentColumn.setValue(value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public <T> List<T> findByParams(Class<T> entityClass, Map<String, Object> params) {

        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columnList = EntityUtils.getColumns(entityClass);

        for (ColumnInfo currentColumn : columnList) {
            try {
                Class currentClass = entityClass;
                Field currentField = currentClass.getDeclaredField(currentColumn.getColumnName());
                currentField.setAccessible(true);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        QueryBuilder queryBuilder = new QueryBuilder();

        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.SELECT);

        for (Map.Entry<String, Object> param : params.entrySet()) {
            Condition condition = new Condition();
            condition.setValue(param.getValue());
            condition.setColumnName(param.getKey());
            queryBuilder.addCondition(condition);
        }


        String query = queryBuilder.createQuery();
        List<T> listInstance = new ArrayList<T>();
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            while (res.next()) {
                T instance = entityClass.newInstance();
                for (ColumnInfo currentColumn : columnList) {
                    Field currentField = instance.getClass().getDeclaredField(currentColumn.getColumnName());
                    currentField.setAccessible(true);
                    currentField.set(instance, EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()), currentField.getType()));

                }
                listInstance.add(instance);
            }
            return listInstance;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
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
        return null;
    }

    @Override
    public <T> void insertAllQueries(List<T> entityClass) {
        Connection conn = DBManager.getConnection();
        try {
            conn.setAutoCommit(false);
            Statement stm = null;
            ResultSet res = null;

            String tableName = EntityUtils.getTableName((Class) entityClass.get(0));
            List<ColumnInfo> columnList = EntityUtils.getColumns((Class) entityClass.get(0));
            for (T entityObject : entityClass) {
                List<String> queries = new ArrayList<String>();
                queries.add(String.valueOf(this.insert(entityObject)));
            }


            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

    }

    public <T> String customInsert(T entity) {
        String tableName = EntityUtils.getTableName(entity.getClass());
        List<ColumnInfo> columnList = EntityUtils.getColumns(entity.getClass());
        Long nextIdValue = Long.valueOf(-1);
        nextIdValue = getaLong((T) entity, tableName, columnList, nextIdValue);

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setTableName(tableName);
        queryBuilder.addQueryColumns(columnList);
        queryBuilder.setQueryType(QueryType.INSERT);
        String query = queryBuilder.createQuery();
        return query;
    }


}
