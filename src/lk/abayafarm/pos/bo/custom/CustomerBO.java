package lk.abayafarm.pos.bo.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.BatchDTO;
import lk.abayafarm.pos.dto.CageDTO;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.entity.Customer;

import java.util.ArrayList;

public interface CustomerBO {
    public boolean saveCustomer(CustomerDTO dto) throws Exception;

    public boolean updateCustomer(CustomerDTO dto) throws Exception;

    public boolean deleteCustomer(String id) throws Exception;

    public CustomerDTO getCustomer(String id) throws Exception;

    public ArrayList<CustomerDTO> getAllCustomer() throws Exception;

    public String getLastCustomerID() throws Exception;

    public CustomerDTO getCustomerInTP(int tp) throws Exception;

}
