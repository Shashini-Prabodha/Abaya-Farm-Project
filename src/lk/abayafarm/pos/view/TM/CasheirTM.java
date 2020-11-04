package lk.abayafarm.pos.view.TM;

import com.jfoenix.controls.JFXButton;

public class CasheirTM {
    private String storeID;
    private String type;
    private int qtyOnStore;
    private double unitPrice;
    private JFXButton btn;

    public CasheirTM() {
    }

    public CasheirTM(String storeID, String type, int qtyOnStore, double unitPrice, JFXButton btn) {
        this.storeID = storeID;
        this.type = type;
        this.qtyOnStore = qtyOnStore;
        this.unitPrice = unitPrice;
        this.btn = btn;
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CasheirTM{" +
                "storeID='" + storeID + '\'' +
                ", type='" + type + '\'' +
                ", qtyOnStore=" + qtyOnStore +
                ", unitPrice=" + unitPrice +
                ", btn=" + btn +
                '}';
    }
}

