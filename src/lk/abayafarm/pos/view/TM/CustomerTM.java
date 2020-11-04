package lk.abayafarm.pos.view.TM;

import com.jfoenix.controls.JFXButton;

public class CustomerTM {
    private String custId;
    private String custName;
    private int custTp;
    private JFXButton btn;

    public CustomerTM() {
    }

    public CustomerTM(String custId, String custName, int custTp, JFXButton btn) {
        this.custId = custId;
        this.custName = custName;
        this.custTp = custTp;
        this.btn = btn;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
                "custId='" + custId + '\'' +
                ", custName='" + custName + '\'' +
                ", custTp=" + custTp +
                ", btn=" + btn +
                '}';
    }
}
