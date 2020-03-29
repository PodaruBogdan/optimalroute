package optimalroute.controller;

import optimalroute.model.User;
import optimalroute.model.UserAccount;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class AdminController {
    private AdminView view;
    private Persistency accountsPersistency;
    public AdminController(AdminView view, Persistency accountsPersistency){
        this.view=view;
        this.accountsPersistency=accountsPersistency;
        view.addAddListener(new AddEmployeeListener());
        view.addRemoveListener(new RemoveEmployeeListener());
        List<UserAccount> employeeList = accountsPersistency.getAll();
        for (UserAccount acc : employeeList) {
            if (!acc.getType().equals("admin")) {
                ((DefaultListModel<String>)view.getList().getModel()).addElement(acc.getUser().getName());
            }
        }

    }
    class RemoveEmployeeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            List<UserAccount> accs=accountsPersistency.getAll();
            String acc="";
            DefaultListModel<String> model = (DefaultListModel<String>) view.getList().getModel();
            for(UserAccount account:accs){
                if(account.getUser().getName().equals(view.getList().getSelectedValue())){
                    acc=account.getUser().getName();
                    accs.remove(account);
                    break;
                }
            }
            if(model.contains(acc)){
                model.removeElement(acc);
                view.getList().setModel(model);
            }
            accountsPersistency.add(accs);
        }
    }
    class AddEmployeeListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            DefaultListModel<String> model = (DefaultListModel<String>) view.getList().getModel();
            List<UserAccount> listm = accountsPersistency.getAll();
            User usr = new User(view.getT1().getText(), view.getT2().getText());
            String id = UUID.randomUUID().toString();
            UserAccount usrAcc = new UserAccount.AccountBuilder().id(id).email(view.getT3().getText()).pswd(view.getT5().getText()).user(usr).username(view.getT4().getText()).build();
            boolean found = false;
            for (UserAccount account : listm) {
                if (account.equals(usrAcc)) {
                    found = true;
                    break;
                }
            }
            if (found == false) {
                listm.add(usrAcc);
                model.addElement(usr.getName());
                view.getList().setModel(model);
                accountsPersistency.add(listm);
            }
        }
    }
}
