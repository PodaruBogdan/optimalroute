package optimalroute.view;

import optimalroute.model.UserAccount;
import optimalroute.model.persistency.Persistency;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginArea extends JPanel {


    private JLabel username;
    private JLabel password;
    private JPasswordField pswField;
    private JTextField usrField;
    private JButton loginEmp;
    private Persistency persistency;
    private List<UserAccount> accountList;
    public LoginArea(Persistency persistency){
        this.persistency=persistency;
        accountList=persistency.getAll();
        loginEmp = new JButton("Log in");
        usrField = new JTextField(20);
        pswField = new JPasswordField(20);
        password = new JLabel("password : ");
        username = new JLabel("username : ");
        JPanel usr = new JPanel();
        usr.add(username);
        usr.add(usrField);
        JPanel pswd = new JPanel();
        pswd.add(password);
        pswd.add(pswField);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(usr);
        this.add(pswd);
        this.add(loginEmp);
        loginEmp.addActionListener(new loginListener());
    }
    class loginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            accountList=persistency.getAll();
            for(UserAccount account:accountList){
                if(account.getUsername().equals(usrField.getText()) && account.getPswd().equals(pswField.getText())){
                    if(account.getType().equals("admin"))
                        new AdminView();
                    else
                        new EmployeeView();
                    break;
                }
            }
        }
    }

}
