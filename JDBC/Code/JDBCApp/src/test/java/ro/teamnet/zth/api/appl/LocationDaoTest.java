package ro.teamnet.zth.api.appl;

import org.junit.Before;
import org.junit.Test;
import ro.teamnet.zth.appl.Department;
import ro.teamnet.zth.appl.Location;
import ro.teamnet.zth.appl.dao.LocationDao;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cosmin.Adamut on 7/14/2017.
 */
public class LocationDaoTest {

    private LocationDao loc;

    @Before
    public void initObjects() {
        loc = new LocationDao();
    }

    @Test
    public void testFindById() {
        Location newLoc = loc.findById(140);
        assertEquals(140, newLoc.getId().intValue());
    }

    @Test
    public void testGetNextIdVal() {
        assertEquals(290, loc.getNextIdVal("locations", "location_id"));
    }

    @Test
    public void testGetInsert() {

        Location location = new Location();
        location.setCity("Manele");
        location.setStreetAddress("Nucului");
        location.setPostalCode("112321");
        location.setStateProvince("Vatican");
        Location returnedLoc = loc.insert(location);
        assertEquals((long) returnedLoc.getId(), loc.getNextIdVal("locations", "location_id") - 1);
    }

    @Test
    public void testFindAll() {

        List<Location> locations = loc.findAll();
        assertEquals(locations.size(), 24);
    }

    @Test
    public void testUpdate() {
        Location location = new Location();
        location.setCity("Brasov");
        location.setStreetAddress("Saturn");
        location.setPostalCode("112");
        location.setStateProvince("Paris");
        location.setId((long) 3201);
        Location returnedLoc = loc.update(location);
        assertEquals("Brasov", returnedLoc.getCity());
    }

}
