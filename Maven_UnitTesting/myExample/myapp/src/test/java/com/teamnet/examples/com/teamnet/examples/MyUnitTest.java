package com.teamnet.examples.com.teamnet.examples;

import com.teamnet.examples.MyUnit;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by Cosmin.Adamut on 7/10/2017.
 */
public class MyUnitTest {
    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();
        String result = myUnit.concatenate("one", "two");
        assertEquals("onetwo", result);
    }

    @Test
    public void testConcatenateNull() {
        MyUnit myUnit = new MyUnit();
        String result = myUnit.concatenate(null, null);
        assertEquals(null, result);
    }

    @Test
    public void testGetBoolean() {
        MyUnit myUnit = new MyUnit();
        assertNotNull(myUnit.getBoolean());
        assertThat(123, is(123));
        assertTrue((Boolean) myUnit.getBoolean() instanceof Boolean);
    }
}
