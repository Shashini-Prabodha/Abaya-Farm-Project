package lk.abayafarm.pos.dto;

public class EmployeeDTO {
    private String employeeId;
    private String employeeName;
    private int employeeTP;
    private String address;
    private double salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String employeeId, String employeeName, int employeeTP, String address, double salary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeTP = employeeTP;
        this.address = address;
        this.salary = salary;
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

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", employeeTP=" + employeeTP +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
