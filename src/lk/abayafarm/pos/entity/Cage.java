package lk.abayafarm.pos.entity;

public class Cage implements SuperEntity{
    private String cageId;
    private String batchId;
    private int maxQty;
    private int availableQty;

    public Cage() {
    }

    public Cage(String cageId, String batchId, int maxQty, int availableQty) {
        this.cageId = cageId;
        this.batchId = batchId;
        this.maxQty = maxQty;
        this.availableQty = availableQty;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    @Override
    public String toString() {
        return "Cage{" +
                "cageId='" + cageId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", maxQty=" + maxQty +
                ", availableQty=" + availableQty +
                '}';
    }
}
