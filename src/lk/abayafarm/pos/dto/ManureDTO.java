package lk.abayafarm.pos.dto;

import java.util.Date;

public class ManureDTO {
    private String cageId;
    private String packSize;
    private int qty;
    private double unitPrice;
    private Date date;

    public ManureDTO() {
    }

    public ManureDTO(String cageId, String packSize, int qty, double unitPrice, Date date) {
        this.cageId = cageId;
        this.packSize = packSize;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.date = date;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
        return "Manure{" +
                "cageId='" + cageId + '\'' +
                ", packSize='" + packSize + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", date='" + date + '\'' +
                '}';
    }
}
