package lk.abayafarm.pos.entity;

public class Store implements SuperEntity{
    private String storeId;
    private String type;
    private int qtyOnStore;
    private double unitPrice;

    public Store() {
    }

    public Store(String storeId, String type, int qtyOnStore, double unitPrice) {
        this.storeId = storeId;
        this.type = type;
        this.qtyOnStore = qtyOnStore;
        this.unitPrice = unitPrice;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQtyOnStore() {
        return qtyOnStore;
    }

    public void setQtyOnStore(int qtyOnStore) {
        this.qtyOnStore = qtyOnStore;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Store{" +
                "storeId='" + storeId + '\'' +
                ", type='" + type + '\'' +
                ", qtyOnStore=" + qtyOnStore +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
