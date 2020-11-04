package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.EmployeeBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.EmployeeDAO;
import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.EmployeeDTO;
import lk.abayafarm.pos.entity.Egg;
import lk.abayafarm.pos.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.EMPLOYEE);

    @Override
    public boolean saveEmployee(EmployeeDTO dto) throws Exception {
        return employeeDAO.save(new Employee(dto.getEmployeeId(),dto.getEmployeeName(),dto.getEmployeeTP(),dto.getAddress(),dto.getSalary()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws Exception {
        return employeeDAO.update(new Employee(dto.getEmployeeId(),dto.getEmployeeName(),dto.getEmployeeTP(),dto.getAddress(),dto.getSalary()));
    }

    @Override
    public boolean deleteEmployee(String id) throws Exception {
        return employeeDAO.delete(id);
    }

    @Override
    public EmployeeDTO getEmployee(String id) throws Exception {
        Employee employee = employeeDAO.get(id);
        return new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeTP(),employee.getAddress(),employee.getSalary());
    }

    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception {
        List<Employee> list = employeeDAO.getAll();
        ArrayList<EmployeeDTO> arrayList=new ArrayList<>();
        for (Employee employee: list) {
            arrayList.add(new EmployeeDTO(employee.getEmployeeId(),employee.getEmployeeName(),employee.getEmployeeTP(),employee.getAddress(),employee.getSalary()));
        }
        return arrayList;
    }

    public String getLastEmployeeID() throws Exception {
        return employeeDAO.getLastEmployeID();
    }
}
