package lk.abayafarm.pos.entity;

public class Batch implements SuperEntity{
    private String batchId;
    private String type;
    private int qty;
    private String status;

    public Batch() {
    }

    public Batch(String batchId, String type, int qty, String status) {
        this.batchId = batchId;
        this.type = type;
        this.qty = qty;
        this.status = status;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId='" + batchId + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", status='" + status + '\'' +
                '}';
    }
}
