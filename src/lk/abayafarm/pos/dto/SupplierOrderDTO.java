package lk.abayafarm.pos.dto;

import java.util.Date;

public class SupplierOrderDTO {
    private String supOrderId;
    private String supplierId;
    private String batchID;
    private String description;
    private int qtyOnHand;
    private double unitPrice;
    private Date date;

    public SupplierOrderDTO() {
    }

    public SupplierOrderDTO(String supOrderId, String supplierId, String batchID, String description, int qtyOnHand, double unitPrice, Date date) {
        this.supOrderId = supOrderId;
        this.supplierId = supplierId;
        this.batchID = batchID;
        this.description = description;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public String getSupOrderId() {
        return supOrderId;
    }

    public void setSupOrderId(String supOrderId) {
        this.supOrderId = supOrderId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getbatchID() {
        return batchID;
    }

    public void setbatchID(String batchID) {
        this.batchID = batchID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SupplierOrderDTO{" +
                "supOrderId=" + supOrderId +
                ", supplierId='" + supplierId + '\'' +
                ", batchID=" + batchID +
                ", description='" + description + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                ", date=" + date +
                '}';
    }
}
