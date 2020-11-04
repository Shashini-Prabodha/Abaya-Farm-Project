package lk.abayafarm.pos.dto;

public class MedicineDTO {
    private String medicineId;
    private String medicineName;
    private int qtyOnHand;
    private String description;


    public MedicineDTO() {
    }

    public MedicineDTO(String medicineId, String medicineName, int qtyOnHand, String description) {
        this.medicineId = medicineId;
        this.medicineName = medicineName;
        this.qtyOnHand = qtyOnHand;
        this.description = description;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MedicineDTO{" +
                "medicineId='" + medicineId + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", description='" + description + '\'' +
                '}';
    }
}
