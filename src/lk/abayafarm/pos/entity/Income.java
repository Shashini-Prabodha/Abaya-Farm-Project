package lk.abayafarm.pos.entity;

import java.util.Date;

public class Income implements SuperEntity{
    private String orderId;
    private Date date;
    private double total;

    public Income() {
    }

    public Income(String orderId, Date date, double total) {
        this.orderId = orderId;
        this.date = date;
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Income{" +
                "orderId=" + orderId +
                ", date='" + date + '\'' +
                ", total=" + total +
                '}';
    }
}
