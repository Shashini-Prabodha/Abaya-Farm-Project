package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dto.EggDTO;
import lk.abayafarm.pos.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.Date;

public interface EmployeeBO {
    public boolean saveEmployee(EmployeeDTO dto) throws Exception;

    public boolean updateEmployee(EmployeeDTO dto) throws Exception;

    public boolean deleteEmployee(String id) throws Exception;

    public EmployeeDTO getEmployee(String id) throws Exception;

    public ArrayList<EmployeeDTO> getAllEmployee() throws Exception;

    public String getLastEmployeeID() throws Exception;


}
