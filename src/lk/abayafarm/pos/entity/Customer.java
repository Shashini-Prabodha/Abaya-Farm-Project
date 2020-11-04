package lk.abayafarm.pos.entity;

public class Customer implements SuperEntity {
    private String custId;
    private String custName;
    private int custTp;

    public Customer() {
    }

    public Customer(String custId, String custName, int custTp) {
        this.custId = custId;
        this.custName = custName;
        this.custTp = custTp;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getCustTp() {
        return custTp;
    }

    public void setCustTp(int custTp) {
        this.custTp = custTp;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", custTp=" + custTp +
                '}';
    }
}


