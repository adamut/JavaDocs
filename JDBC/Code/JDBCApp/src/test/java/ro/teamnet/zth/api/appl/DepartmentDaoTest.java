package ro.teamnet.zth.api.appl;

import org.junit.Before;
import org.junit.Test;
import ro.teamnet.zth.appl.Department;
import ro.teamnet.zth.appl.dao.DepartmentDao;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cosmin.Adamut on 7/14/2017.
 */
public class DepartmentDaoTest {
    private DepartmentDao dep;

    @Before
    public void initObjects() {
        dep = new DepartmentDao();
    }

    @Test
    public void testFindById() {
        Department newDep = dep.findById(140);
        assertEquals(140, newDep.getId().intValue());
    }

    @Test
    public void testGetNextIdVal() {
        assertEquals(290, dep.getNextIdVal("departments", "department_id"));
    }

    @Test
    public void testGetInsert() {

        Department department = new Department();
        department.setDepartmentName("LastDepartment");
        department.setLocation(Long.valueOf(1700));
        Department returnedDep = dep.insert(department);
        assertEquals((long) returnedDep.getId(), dep.getNextIdVal("departments", "department_id") - 1);
    }

    @Test
    public void testFindAll() {

        List<Department> departments = dep.findAll();
        assertEquals(departments.size(), 46);
    }

    @Test
    public void testUpdate() {
        Department department = new Department();
        department.setDepartmentName("LastUpdate");
        department.setLocation(Long.valueOf(1700));
        department.setId((long) 280);
        Department returnedDep = dep.update(department);
        assertEquals("LastUpdate", returnedDep.getDepartmentName());
    }

    @Test
    public void testEmployeesFromDepartments() {
        assertEquals(1,dep.employeesFromDepartments("str").size());
    }



}
