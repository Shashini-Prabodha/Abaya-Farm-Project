package lk.abayafarm.pos.dto;

public class OrderDetailsDTO {
    private String orderId;
    private String storeId;
    private int orderQty;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String orderId, String storeId, int orderQty) {
        this.orderId = orderId;
        this.storeId = storeId;
        this.orderQty = orderQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "orderId=" + orderId +
                ", storeId='" + storeId + '\'' +
                ", orderQty=" + orderQty +
                '}';
    }
}
