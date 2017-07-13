package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Cosmin.Adamut on 7/13/2017.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection(){
        assertNotNull(DBManager.getConnection());
    }
    @Test
    public void testCheckConnection(){
        assertEquals(1,DBManager.checkConnection(DBManager.getConnection()));
    }
}
