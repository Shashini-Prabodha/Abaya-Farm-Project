package lk.abayafarm.pos.entity;

import java.util.Date;

public class Order implements SuperEntity{
    private String orderId;
    private String custId;
    private Date date;

    public Order() {
    }

    public Order(String orderId, String custId, Date date) {
        this.orderId = orderId;
        this.custId = custId;
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", custId='" + custId + '\'' +
                ", date=" + date +
                '}';
    }
}
