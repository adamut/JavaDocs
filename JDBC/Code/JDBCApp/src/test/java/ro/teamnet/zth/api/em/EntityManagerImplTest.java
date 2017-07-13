package ro.teamnet.zth.api.em;

import org.junit.Test;
import ro.teamnet.zth.appl.domain.Department;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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

        EntityManagerImpl emp = new EntityManagerImpl();
        List<Department> departments = new ArrayList<Department>();
        departments = emp.findAll(Department.class);
        assertEquals(departments.size(),33);
    }
}
