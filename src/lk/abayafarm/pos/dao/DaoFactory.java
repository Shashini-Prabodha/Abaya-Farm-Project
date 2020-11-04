package lk.abayafarm.pos.dao;

import lk.abayafarm.pos.dao.custom.impl.*;

public class DaoFactory {
    private static DaoFactory daoFactory;

    private DaoFactory() {
    }
    public static DaoFactory getInstance() {
        if(null==daoFactory){
            daoFactory=new DaoFactory();
        }return daoFactory;
    }

    public enum DaoType{
        BATCH,CAGE,CUSTOMER,EGG,FEED,FEEDDETAILS,EMPLOYEE,INCOME,LOGIN,MANURE,
        MEDICINE,MEDICINEDETAILS,ORDER,ORDERDETAILS,STORE,SUPPLIER,SUPPLIERORDER,QUERY
    }

    public <T extends SuperDAO> T getDao(DaoType type){
        switch (type){
            case BATCH:
                return (T) new BatchDAOImpl();
            case CAGE:
                return (T) new CageDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case EGG:
                return (T) new EggDAOImpl();
            case FEED:
                return (T) new FeedDAOImpl();
            case FEEDDETAILS:
                return (T) new FeedDetailsDAOImpl();
            case EMPLOYEE:
                return (T) new EmployeeDAOImpl();
            case INCOME:
                return (T) new IncomeDAOImpl();
            case LOGIN:
                return (T) new LoginDAOImpl();
            case MANURE:
                return (T) new ManureDAOImpl();
            case MEDICINE:
                return (T) new MedicineDAOImpl();
            case MEDICINEDETAILS:
                return (T) new MedicineDetailsDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDERDETAILS:
                return (T) new OrderDetailsDAOImpl();
            case STORE:
                return (T) new StoreDAOImpl();
            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            case SUPPLIERORDER:
                return (T) new SupplierOrderDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }

    }
}
