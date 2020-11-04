package lk.abayafarm.pos.dao.custom.impl;

import lk.abayafarm.pos.dao.CrudUtil;
import lk.abayafarm.pos.dao.custom.EmployeeDAO;
import lk.abayafarm.pos.entity.Customer;
import lk.abayafarm.pos.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(Employee employee) throws Exception {
        return CrudUtil.execute("INSERT INTO employee VALUES (?,?,?,?,?)", employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeTP(), employee.getAddress(), employee.getSalary());
    }

    @Override
    public boolean update(Employee employee) throws Exception {
        return CrudUtil.execute("UPDATE employee SET employeeName=?,employeeTP=?, address=?, salary=? WHERE employeeId=?", employee.getEmployeeName(), employee.getEmployeeTP(), employee.getAddress(), employee.getSalary(), employee.getEmployeeId());

    }

    @Override
    public boolean delete(String s) throws Exception {
        return CrudUtil.execute("DELETE FROM employee WHERE employeeId=?", s);
    }

    @Override
    public Employee get(String s) throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee WHERE employeeId=?", s);
        if (rst.next()) {
            return new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public List<Employee> getAll() throws Exception {
        ResultSet rst = CrudUtil.execute("SELECT * FROM employee");
        ArrayList<Employee> list = new ArrayList();
        while (rst.next()) {
            list.add(
                    new Employee(
                            rst.getString(1),
                            rst.getString(2),
                            rst.getInt(3),
                            rst.getString(4),
                            rst.getDouble(5)
                    )
            );
        }
        return list;
    }

    @Override
    public String getLastEmployeID() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.execute("SELECT employeeId FROM employee ORDER BY employeeId DESC LIMIT 1");
        String id=null;
        if(rst.next()){
            id=rst.getString(1);
        }
        return id;
    }
}
