package lk.abayafarm.pos.dto;

public class FeedDTO {
    private String feedId;
    private int qtyOnHand;
    private String feedType;
    private int packSize;

    public FeedDTO() {
    }

    public FeedDTO(String feedId, int qtyOnHand, String feedType, int packSize) {
        this.feedId = feedId;
        this.qtyOnHand = qtyOnHand;
        this.feedType = feedType;
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

    public String getFeedType() {
        return feedType;
    }

    public void setFeedType(String feedType) {
        this.feedType = feedType;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "feedId='" + feedId + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", feedType='" + feedType + '\'' +
                ", packSize='" + packSize + '\'' +
                '}';
    }
}
