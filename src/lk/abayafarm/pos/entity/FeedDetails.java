package lk.abayafarm.pos.entity;

import java.util.Date;

public class FeedDetails implements SuperEntity{
    String feedId;
    String cageId;
    int term;
    int usedQty;
    Date date;

    public FeedDetails() {
    }

    public FeedDetails(String feedId, String cageId, int term, int usedQty, Date date) {
        this.feedId = feedId;
        this.cageId = cageId;
        this.term = term;
        this.usedQty = usedQty;
        this.date = date;
    }

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getUsedQty() {
        return usedQty;
    }

    public void setUsedQty(int usedQty) {
        this.usedQty = usedQty;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FeedDetails{" +
                "feedId='" + feedId + '\'' +
                ", cageId='" + cageId + '\'' +
                ", term=" + term +
                ", usedQty=" + usedQty +
                ", date=" + date +
                '}';
    }
}
