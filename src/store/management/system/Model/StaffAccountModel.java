package store.management.system.Model;

public class StaffAccountModel {
    private String staffID;
    private String staffName;
    private String staffEmail;
    private String password;

    public StaffAccountModel(String staffID, String staffName, String staffEmail, String password) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffEmail = staffEmail;
        this.password = password;
    }

    // Getters and setters
    public String getStaffID() { return staffID; }
    public void setStaffID(String staffID) { this.staffID = staffID; }

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public String getStaffEmail() { return staffEmail; }
    public void setStaffEmail(String staffEmail) { this.staffEmail = staffEmail; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
