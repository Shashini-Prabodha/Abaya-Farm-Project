package lk.abayafarm.pos.entity;

public class Supplier implements SuperEntity{
    private String supplierId;
    private String supplierName;
    private int supplierTp;
    private String supplierAddress;
    private String type;

    public Supplier() {
    }

    public Supplier(String supplierId, String supplierName, int supplierTp, String supplierAddress, String type) {
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.supplierTp = supplierTp;
        this.supplierAddress = supplierAddress;
        this.type = type;
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

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId='" + supplierId + '\'' +
                ", supplierName='" + supplierName + '\'' +
                ", supplierTp=" + supplierTp +
                ", supplierAddress='" + supplierAddress + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
