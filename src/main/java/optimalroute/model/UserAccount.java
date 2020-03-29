package optimalroute.model;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private String id;
    private String username;
    private String pswd;
    private String email;
    private User user;

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

    private UserAccount(AccountBuilder builder){
        this.username=builder.username;
        this.user=builder.user;
        this.pswd=builder.pswd;
        this.email=builder.email;
        this.id=builder.id;
    }

    public static class AccountBuilder {
        private String id;
        private String username;
        private String pswd;
        private String email;
        private User user;

        public AccountBuilder id(String id) {
            this.id = id;
            return this;
        }

        public AccountBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AccountBuilder pswd(String pswd) {
            this.pswd = pswd;
            return this;
        }

        public AccountBuilder email(String email) {
            this.email = email;
            return this;
        }

        public AccountBuilder user(User user) {
            this.user = user;
            return this;
        }
        public UserAccount build(){
            return new UserAccount(this);
        }
    }







}
