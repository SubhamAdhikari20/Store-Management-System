package store.management.system.Model;


public class AdminStaffModel {
    private String staffID;
    private String staffName;
    private String staffGender;
    private String staffContact;
    private String setPassword;
    private String confirmPassword;

    public AdminStaffModel(String staffID, String staffName, String staffGender, String staffContact, String setPassword, String confirmPassword) {
        this.staffID = staffID;
        this.staffName = staffName;
        this.staffGender = staffGender;
        this.staffContact = staffContact;
        this.setPassword = setPassword;
        this.confirmPassword = confirmPassword;
    }
    
    public String getStaffID(){
        return staffID;
    }
    
    public String getStaffName(){
        return staffName;
    }
    
    public String getStaffGender(){
        return staffGender;
    }
    
    public String getStaffContact(){
        return staffContact;
    }
    
    public String getStaffPassword(){
        return setPassword;
    }
    
    public String getStaffConfirmPassword(){
        return confirmPassword;
    }
    
    
    
    
    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStaffGender(String staffGender) {
        this.staffGender = staffGender;
    }

    public void setStaffContact(String staffContact) {
        this.staffContact = staffContact;
    }

    public void setStaffPassword(String staffPassword) {
        this.setPassword = staffPassword;
    }
    
    public void setStaffConfrimPassword(String staffConfirmPassword) {
        this.confirmPassword = staffConfirmPassword;
    }
    
}
