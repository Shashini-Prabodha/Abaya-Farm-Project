package lk.abayafarm.pos.dao.custom;

import lk.abayafarm.pos.dao.CrudDAO;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    String getCustomerLasiID() throws Exception;
    public Customer getCustomerInTP(int tp) throws Exception;
}
