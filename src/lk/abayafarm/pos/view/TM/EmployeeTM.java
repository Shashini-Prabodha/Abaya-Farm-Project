package lk.abayafarm.pos.view.TM;

import com.jfoenix.controls.JFXButton;

public class EmployeeTM {
    private String employeeId;
    private String employeeName;
    private int employeeTP;
    private String address;
    private double salary;
    private JFXButton btn;

    public EmployeeTM() {
    }

    public EmployeeTM(String employeeId, String employeeName, int employeeTP, String address, double salary, JFXButton btn) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeTP = employeeTP;
        this.address = address;
        this.salary = salary;
        this.btn = btn;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getEmployeeTP() {
        return employeeTP;
    }

    public void setEmployeeTP(int employeeTP) {
        this.employeeTP = employeeTP;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "EmployeeTM{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeTP=" + employeeTP +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                ", btn=" + btn +
                '}';
    }
}
