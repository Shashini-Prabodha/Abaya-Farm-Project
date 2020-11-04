package lk.abayafarm.pos.entity;

import java.sql.Date;
import java.time.LocalDate;

public class Egg implements SuperEntity{
    private String cageId;
    private String storeId;
    private String type;
    private int qty;
    private int term;
    private int damage;
    private Date date;


    public Egg(String cageId, String storeId, String type, int qty, int term, int damage, Date date) {
        this.cageId = cageId;
        this.storeId = storeId;
        this.type = type;
        this.qty = qty;
        this.term = term;
        this.damage = damage;
        this.date = date;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
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

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "Egg{" +
                "cageId='" + cageId + '\'' +
                ", storeId='" + storeId + '\'' +
                ", type='" + type + '\'' +
                ", qty=" + qty +
                ", term=" + term +
                ", damage=" + damage +
                ", date=" + date +
                '}';
    }
}
