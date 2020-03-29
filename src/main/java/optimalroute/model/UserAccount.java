package optimalroute.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String id;
    private String username;
    private String pswd;
    private String email;
    private User user;

    public UserAccount(String username,String id,String pswd,String email,User user){
        this.username=username;
        this.id=id;
        this.email=email;
        this.pswd=pswd;
        this.user=user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return user.getRole();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public boolean equals(Object obj){
        UserAccount acc = (UserAccount)obj;
        if (
                        this.getPswd().equals(acc.getPswd()) &&
                        this.getUsername().equals(acc.getUsername())        )
        {
            return true;
        }
        return false;
    }

}
