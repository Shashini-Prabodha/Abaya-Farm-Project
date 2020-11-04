package lk.abayafarm.pos.dto;

import java.util.Date;

public class OrderDTO {
    private String orderId;
    private String custId;
    private Date date;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String custId, Date date) {
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
