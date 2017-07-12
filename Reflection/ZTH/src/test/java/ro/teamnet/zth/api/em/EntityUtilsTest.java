package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.api.annotations.Table;
import ro.teamnet.zth.appl.domain.Department;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cosmin.Adamut on 7/12/2017.
 */
public class EntityUtilsTest {

    @Test
    public void testGetTableNameMethod() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String tableName = EntityUtils.getTableName(Department.class);
        assertEquals("Table name should be departments!", "departments", tableName);
    }

    @Test
    public void testGetColumnName() {
        Class entity = Department.class;
        int size = EntityUtils.getColumnName(entity).size();
        assertEquals(3, size);
    }
    @Test
    public void testCastFromSqlType() {

      //  assertEquals(,EntityUtils.castFromSqlType(BigDecimal,Integer));
    }
}
