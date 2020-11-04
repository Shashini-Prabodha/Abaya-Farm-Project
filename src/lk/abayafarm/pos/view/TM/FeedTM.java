package lk.abayafarm.pos.view.TM;

import java.util.Date;

public class FeedTM {

    private String feedId;
    private int qtyOnHand;
    private String type;
    private int packSize;

    public FeedTM() {
    }

    public FeedTM(String feedId, int qtyOnHand, String type, int packSize) {
        this.feedId = feedId;
        this.qtyOnHand = qtyOnHand;
        this.type = type;
        this.packSize = packSize;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    @Override
    public String toString() {
        return "FeedTM{" +
                "feedId='" + feedId + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", type='" + type + '\'' +
                ", packSize=" + packSize +
                '}';
    }
}
