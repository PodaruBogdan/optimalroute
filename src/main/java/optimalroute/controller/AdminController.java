package optimalroute.controller;

import optimalroute.model.User;
import optimalroute.model.UserAccount;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.AdminView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
        view.addUpdateListener(new UpdateEmployeeListener());
        view.addListListener(new ListListener());
        List<UserAccount> employeeList = accountsPersistency.getAll();
        for (UserAccount acc : employeeList) {
            if (!acc.getType().equals("admin")) {
                ((DefaultListModel<String>)view.getList().getModel()).addElement(acc.getUser().getName());
            }
        }

    }
    class ListListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            List<UserAccount> accs=accountsPersistency.getAll();
            UserAccount acc=null;
            DefaultListModel<String> model = (DefaultListModel<String>) view.getList().getModel();
            for(UserAccount account:accs){
                if(account.getUser().getName().equals(view.getList().getSelectedValue())){
                    acc=account;
                    break;
                }
            }
            if(acc!=null) {
                view.getT1().setText(acc.getUser().getRole());
                view.getT2().setText(acc.getUser().getName());
                view.getT3().setText(acc.getEmail());
                view.getT4().setText(acc.getUsername());
                view.getT5().setText(acc.getPswd());
            }
        }
    }
    class UpdateEmployeeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<UserAccount> accs=accountsPersistency.getAll();
            UserAccount acc=null;
            DefaultListModel<String> model = (DefaultListModel<String>) view.getList().getModel();
            for(UserAccount account:accs){
                if(account.getUser().getName().equals(view.getList().getSelectedValue())){
                    acc=account;
                    break;
                }
            }
            if(acc!=null) {
                acc.getUser().setRole(view.getT1().getText());
                acc.getUser().setName(view.getT2().getText());
                acc.setEmail(view.getT3().getText());
                acc.setUsername(view.getT4().getText());
                acc.setPswd(view.getT5().getText());
                accountsPersistency.add(accs);
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
