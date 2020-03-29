package optimalroute.model;


import java.io.Serializable;

public class User implements Serializable {
    private String role;
    private String name;

    public User(String role, String name) {
        this.role = role;
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object obj) {
        User usr = (User) obj;
        if(usr.getRole().equals(this.getRole()) && usr.getName().equals(this.getName()))
            return true;
        return false;
    }



}
