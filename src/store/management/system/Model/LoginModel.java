package store.management.system.Model;


        

public class LoginModel {
    private String username;
    private String password;
    public LoginModel(){
        
    }
    public LoginModel(String username, String password){
        this.username = username;
        this.password = password;
        
    }
    
    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
    
}
