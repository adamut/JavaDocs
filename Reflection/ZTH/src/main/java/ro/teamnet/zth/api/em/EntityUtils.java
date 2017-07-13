package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;
import sun.text.resources.en.FormatData_en_IE;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin.Adamut on 7/12/2017.
 */
public class EntityUtils {

    public EntityUtils() {
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        if (entity.getAnnotations().length == 0)
            return entity.getName();
        if (entity.isAnnotationPresent(Table.class)) {
//           Annotation ann= entity.getAnnotation(Table.class);
//           Method meth= ann.annotationType().getMethod("name");
            //return meth.invoke(ann).toString();
            Table table = (Table) entity.getAnnotation(Table.class);
            return table.name();
        }

        return entity.getName();
    }

    public static List<ColumnInfo> getColumnName(Class entity) {
        List<ColumnInfo> colInfo = new ArrayList<ColumnInfo>();
        Field[] fields = entity.getDeclaredFields();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].isAnnotationPresent(Column.class)) {
                ColumnInfo currentColumn = new ColumnInfo();
                currentColumn.setColumnName(fields[i].getName());
                currentColumn.setColumnType(fields[i].getType());
                currentColumn.setDbColumnName(fields[i].getAnnotation(Column.class).name());
                currentColumn.setId(false);
                colInfo.add(currentColumn);
            } else if (fields[i].isAnnotationPresent(Id.class)) {
                ColumnInfo currentColumn = new ColumnInfo();
                currentColumn.setColumnName(fields[i].getName());
                currentColumn.setColumnType(fields[i].getType());
                currentColumn.setDbColumnName(fields[i].getAnnotation(Id.class).name());
                currentColumn.setId(true);
                colInfo.add(currentColumn);
            }
        }
        return colInfo;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value == null)
            return null;
        if (value instanceof BigDecimal) {
            BigDecimal b = (BigDecimal) value;
            if (wantedType.equals(Integer.class)) {
                return b.intValue();
            }
            if (wantedType.equals(Long.class)) {
                return b.longValue();
            }
            if (wantedType.equals(Float.class)) {
                return b.floatValue();
            }
            if (wantedType.equals(Double.class)) {
                return b.doubleValue();
            }
        }

        return value;

    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> listFields = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(annotation) != null)
                listFields.add(field);
        }
        return listFields;

    }

    public static Object getSqlValue(Object object) throws NoSuchFieldException {
        if (object.getClass().getAnnotation(Table.class) != null) {
            Field id = object.getClass().getField("id");
            id.setAccessible(true);
            return id.getType();
        }
        return object;

    }

   /* public static void main(String[] args) {
        try {
            EntityUtils.getTableName(Department.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        EntityUtils.getColumnName(Department.class);
        EntityUtils.getFieldsByAnnotations(Department.class, Column.class);
    }*/
}
