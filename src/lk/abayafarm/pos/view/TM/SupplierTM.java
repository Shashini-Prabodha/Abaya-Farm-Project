package lk.abayafarm.pos.view.TM;

import com.jfoenix.controls.JFXButton;
import lk.abayafarm.pos.entity.SuperEntity;

public class SupplierTM {
    private String supplierId;
    private String supplierName;
    private int supplierTp;
    private String supplierAddress;
    private String type;
    private JFXButton btn;


    public SupplierTM() {
    }

    public SupplierTM(String supplierId, String supplierName, int supplierTp, String supplierAddress, String type, JFXButton btn) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierTp = supplierTp;
        this.supplierAddress = supplierAddress;
        this.type = type;
        this.btn = btn;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierTp() {
        return supplierTp;
    }

    public void setSupplierTp(int supplierTp) {
        this.supplierTp = supplierTp;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(String supplierAddress) {
        this.supplierAddress = supplierAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "SupplierTM{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierTp=" + supplierTp +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", type='" + type + '\'' +
                ", btn=" + btn +
                '}';
    }
}
