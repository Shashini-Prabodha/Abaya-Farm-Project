package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    String getLastEmployeID() throws SQLException, ClassNotFoundException;
}
