package lk.abayafarm.pos.bo;

import lk.abayafarm.pos.bo.custom.impl.*;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        if (null == boFactory) {
            boFactory = new BoFactory();
        }
        return boFactory;
    }

    public enum BoType {
        BATCH,  CAGE, CUSTOMER, EGG, FEED, FEEDDETAILS, EMPLOYEE, INCOME, LOGIN, MANURE,
        MEDICINE, MEDICINEDETAILS, ORDER, ORDERDETAILS, STORE, SUPPLIER,
        SUPPLIERORDER, ADDITEMTOSTORE, ADDBATCH, UPDATEBATCH, SELLBATCH,
        SUPPLIERITEMORDER, FEEDSERVING, USEMEDICINE, PLACEORDER
    }

    public <T> T getBo(BoType type) {
        switch (type) {
            case BATCH:
                return (T) new BatchBOImpl();
            case CAGE:
                return (T) new CageBOImpl();
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case EGG:
                return (T) new EggBOImpl();
            case FEED:
                return (T) new FeedBOImpl();
            case FEEDDETAILS:
                return (T) new FeedDetailsBOImpl();
            case EMPLOYEE:
                return (T) new EmployeeBOImpl();
            case INCOME:
                return (T) new IncomeBOImpl();
            case LOGIN:
                return (T) new LoginBOImpl();
            case MANURE:
                return (T) new ManureBOImpl();
            case MEDICINE:
                return (T) new MedicineBOImpl();
            case MEDICINEDETAILS:
                return (T) new MedicineDetailsBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            case ORDERDETAILS:
                return (T) new OrderDetailsBOImpl();
            case STORE:
                return (T) new StoreBOImpl();
            case SUPPLIER:
                return (T) new SupplierBOImpl();
            case SUPPLIERORDER:
                return (T) new SupplierOrderBOImpl();
            case ADDITEMTOSTORE:
                return (T) new AddItemtoStoreBOImpl();
            case ADDBATCH:
                return (T) new AddBatchBOImpl();
            case UPDATEBATCH:
                return (T) new UpdateBatchBOImpl();
            case SELLBATCH:
                return (T) new SellBatchBOImpl();
            case SUPPLIERITEMORDER:
                return (T) new SupplierItemOrderTransactionBOImpl();
            case FEEDSERVING:
                return (T) new FeedServingBOImpl();
            case USEMEDICINE:
                return (T) new UseMedicineBOImpl();
            case PLACEORDER:
                return (T) new PlaceOrderBOImpl();
            default:
                return null;
        }

    }
}
