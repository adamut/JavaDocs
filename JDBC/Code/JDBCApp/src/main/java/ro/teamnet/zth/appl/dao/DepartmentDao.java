package ro.teamnet.zth.appl.dao;

import ro.teamnet.zth.api.database.DBManager;
import ro.teamnet.zth.api.em.ColumnInfo;
import ro.teamnet.zth.api.em.EntityManager;
import ro.teamnet.zth.api.em.EntityManagerImpl;
import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.Department;
import ro.teamnet.zth.appl.Employee;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cosmin.Adamut on 7/14/2017.
 */
public class DepartmentDao {
    EntityManager entityManager = new EntityManagerImpl();

    public Department findById(long id) {
        return entityManager.findById(Department.class, id);
    }

    public long getNextIdVal(String tableName, String columnIdName) {
        return entityManager.getNextIdVal(tableName, columnIdName);
    }

    public Department insert(Department dep) {
        return (Department) entityManager.insert(dep);
    }

    public List<Department> findAll() {
        return (List<Department>) entityManager.findAll(Department.class);
    }

    public Department update(Department entity) {
        return entityManager.update(entity);
    }

    public List<Employee> employeesFromDepartments(String partName) {
        Connection conn = DBManager.getConnection();
        Statement stm = null;
        ResultSet res = null;
        String query = "SELECT * FROM employees WHERE department_id IN (SELECT department_id FROM departments WHERE lower(department_name) LIKE '%"+partName+"%' ) ";
        List<Employee> employeeList = new ArrayList<Employee>();
        List<ColumnInfo> columnList = EntityUtils.getColumns(Employee.class);
        try {
            stm = conn.createStatement();
            res = stm.executeQuery(query);
            while (res.next()) {
                Employee employee = new Employee();
                for (ColumnInfo currentColumn : columnList) {
                    Field field = employee.getClass().getDeclaredField(currentColumn.getColumnName());
                    field.setAccessible(true);
                    field.set(employee, EntityUtils.castFromSqlType(res.getObject(currentColumn.getDbColumnName()), field.getType()));
                }
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return employeeList;
    }



    public static void main(String[] args) {
        DepartmentDao depart = new DepartmentDao();
        depart.employeesFromDepartments("fin");

    }
}
