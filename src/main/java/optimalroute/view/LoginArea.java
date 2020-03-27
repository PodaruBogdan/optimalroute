package optimalroute.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginArea extends JPanel {


    private JLabel username;
    private JLabel password;
    private JPasswordField pswField;
    private JTextField usrField;
    private JButton login;
    public LoginArea(){
        login = new JButton("Log in");
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
        this.add(login);
        login.addActionListener(new loginListener());
    }
    class loginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            new EmployeeView();
        }
    }

}
