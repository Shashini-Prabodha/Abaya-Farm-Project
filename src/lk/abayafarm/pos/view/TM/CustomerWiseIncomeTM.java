package lk.abayafarm.pos.view.TM;

public class CustomerWiseIncomeTM {
    private String custId;
    private String name;
    private double total;

    public CustomerWiseIncomeTM() {
    }

    public CustomerWiseIncomeTM(String custId, String name, double total) {
        this.custId = custId;
        this.name = name;
        this.total = total;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "CustomerWiseIncomeTM{" +
                "custId='" + custId + '\'' +
                ", name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
