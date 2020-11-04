package lk.abayafarm.pos.view.TM;

import com.jfoenix.controls.JFXButton;

public class BatchTM {
    private int BatchId;
    private String type;
    private String getQty;
    private String availableQty;
    private String status;
    private JFXButton btn;

    public BatchTM() {
    }

    public BatchTM(int batchId, String type, String getQty, String availableQty, String status, JFXButton btn) {
        BatchId = batchId;
        this.type = type;
        this.getQty = getQty;
        this.availableQty = availableQty;
        this.status = status;
        this.btn = btn;
    }

    public int getBatchId() {
        return BatchId;
    }

    public void setBatchId(int batchId) {
        BatchId = batchId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGetQty() {
        return getQty;
    }

    public void setGetQty(String getQty) {
        this.getQty = getQty;
    }

    public String getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(String availableQty) {
        this.availableQty = availableQty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "BatchTM{" +
                "BatchId=" + BatchId +
                ", type='" + type + '\'' +
                ", getQty='" + getQty + '\'' +
                ", availableQty='" + availableQty + '\'' +
                ", status='" + status + '\'' +
                ", btn=" + btn +
                '}';
    }
}
