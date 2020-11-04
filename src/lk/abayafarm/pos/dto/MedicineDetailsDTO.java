package lk.abayafarm.pos.dto;

import java.util.Date;

public class MedicineDetailsDTO {
    private String medicineID;
    private String cageId;
    private int usedQty;
    private Date date;

    public MedicineDetailsDTO() {
    }

    public MedicineDetailsDTO(String medicineID, String cageId, int usedQty, Date date) {
        this.medicineID = medicineID;
        this.cageId = cageId;
        this.usedQty = usedQty;
        this.date = date;
    }

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getCageId() {
        return cageId;
    }

    public void setCageId(String cageId) {
        this.cageId = cageId;
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
        return "MedicineDetailsDTO{" +
                "medicineID='" + medicineID + '\'' +
                ", cageId='" + cageId + '\'' +
                ", usedQty=" + usedQty +
                ", date=" + date +
                '}';
    }
}
