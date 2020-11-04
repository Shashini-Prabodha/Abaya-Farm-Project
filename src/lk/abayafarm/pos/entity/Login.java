package lk.abayafarm.pos.entity;

public class Login implements SuperEntity{
    private String userName;
    private String password;
    private String roll;

    public Login(String string) {
    }

    public Login(String userName, String password, String roll) {
        this.userName = userName;
        this.password = password;
        this.roll = roll;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roll='" + roll + '\'' +
                '}';
    }
}
