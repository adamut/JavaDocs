package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.Department;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Cosmin.Adamut on 7/13/2017.
 */
public class EntityManagerImplTest {
    @Test
    public void testFindById() {
        EntityManagerImpl entityManager = new EntityManagerImpl();
        Department department = entityManager.findById(Department.class, new Long(10));

        assertEquals(department.getDepartmentName(), "Administration");
    }

    @Test
    public void testGetNextIdVal() {
        EntityManagerImpl emp = new EntityManagerImpl();
        assertEquals(emp.getNextIdVal("EMPLOYEES", "salary"), 24001);
    }


    @Test
    public void testGetInsert() {
        EntityManagerImpl emp = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("MyDep");
        department.setLocation(Long.valueOf(1700));
        Department returnedDep = (Department) emp.insert(department);
        assertEquals((long)returnedDep.getId(),emp.getNextIdVal("DEPARTMENTS", "department_id")-1);
    }

    @Test
    public void testFindAll() {
        //merge
        EntityManagerImpl emp = new EntityManagerImpl();
        List<Department> departments = new ArrayList<Department>();
        departments = emp.findAll(Department.class);
        assertEquals(departments.size(),33);
    }

    @Test
    public void testUpdate(){
        EntityManagerImpl emp = new EntityManagerImpl();


        Department department = new Department();
        department.setDepartmentName("Test");
        department.setLocation(Long.valueOf(1700));
        department.setId((long) 280);
        Department returnedDep = (Department) emp.update(department);
        assertEquals("Test", returnedDep.getDepartmentName());
        //TODO testam


    }

    @Test
    public void testDelete(){
        EntityManagerImpl emp = new EntityManagerImpl();
        Department department = new Department();
        department.setDepartmentName("DeleteDep");
        department.setLocation(Long.valueOf(1700));
        Department returnedDep = (Department) emp.insert(department);
        department.setId(Long.valueOf(287));
        emp.delete(department);
        assertNull(department.getDepartmentName(),emp.findById(Department.class, department.getId()));
    }

    @Test
    public void testFindByParams(){
        EntityManagerImpl emp = new EntityManagerImpl();


       // Department.class , hash map put diferite key (nume coloana) valoare valoare din tabel
        Map<String,Object> conditionsMap = new HashMap<String,Object>();
        conditionsMap.put("location_id", 1700);
        conditionsMap.put("department_name","DeleteDep");
        List<Department> list = new ArrayList<Department>();
        list = emp.findByParams(Department.class,conditionsMap);
        assertEquals(7,list.size());
    }


    @Test
    public void testInsertAllQueries(){
        EntityManagerImpl emp = new EntityManagerImpl();


        //TODO mai facem si maine
        //assertEquals(7,list.size());
    }


}
