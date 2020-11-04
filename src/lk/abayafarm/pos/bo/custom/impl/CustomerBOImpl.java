package lk.abayafarm.pos.bo.custom.impl;

import lk.abayafarm.pos.bo.custom.CustomerBO;
import lk.abayafarm.pos.dao.DaoFactory;
import lk.abayafarm.pos.dao.custom.CustomerDAO;
import lk.abayafarm.pos.dto.CustomerDTO;
import lk.abayafarm.pos.dto.SupplierOrderDTO;
import lk.abayafarm.pos.entity.Customer;
import lk.abayafarm.pos.entity.SupplierOrder;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.save(new Customer(dto.getCustId(),dto.getCustName(),dto.getCustTp()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.update(new Customer(dto.getCustId(),dto.getCustName(),dto.getCustTp()));
    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO getCustomer(String id) throws Exception {
        Customer customer=customerDAO.get(id);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustTp());
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomer() throws Exception {
        List<Customer> list = customerDAO.getAll();
        ArrayList<CustomerDTO> arrayList = new ArrayList<>();
        for (Customer customer : list) {
            arrayList.add(new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustTp()));
        }
        return arrayList;
    }

    @Override
    public String getLastCustomerID() throws Exception {
        return customerDAO.getCustomerLasiID();
    }

    @Override
    public CustomerDTO getCustomerInTP(int tp) throws Exception {
        Customer customer=customerDAO.getCustomerInTP(tp);
        return new CustomerDTO(customer.getCustId(),customer.getCustName(),customer.getCustTp());
    }
}
