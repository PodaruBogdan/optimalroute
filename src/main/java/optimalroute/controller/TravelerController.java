package optimalroute.controller;
import optimalroute.model.StationNode;
import optimalroute.model.User;
import optimalroute.model.UserAccount;
import optimalroute.model.persistency.Persistency;
import optimalroute.view.AdminView;
import optimalroute.view.EmployeeView;
import optimalroute.view.TravelerView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TravelerController {

    private TravelerView view;
    private Persistency accountsPersistency;
    private Persistency stationsPersistency;

    public TravelerController(TravelerView view, Persistency accountsPersistency, Persistency stationsPersistency){
        this.view=view;
        this.accountsPersistency=accountsPersistency;
        this.stationsPersistency=stationsPersistency;
        List<StationNode> stationNodes = stationsPersistency.getAll();
        view.getBusLinesArea().setStationNodes(stationNodes);
        String id = UUID.randomUUID().toString();
        User user=new User("admin","Podaru Bogdan");
        UserAccount admin = new UserAccount.AccountBuilder().id(id).email("podaru.bogdan98@gmail.com").pswd("1234").user(user).username("bogdan").build();
        List<UserAccount> accs= accountsPersistency.getAll();
        boolean found=false;
        for(UserAccount account:accs){
            if(account.getUsername().equals(admin.getUsername())){
                found=true;
                break;
            }
        }
        if(found==false){
            accs.add(admin);
            accountsPersistency.add(accs);
        }
        for(String s:getStationNames(stationNodes)){
           ((DefaultListModel)view.getBusLinesListing().getList().getModel()).addElement("Line : "+s);
        }

        view.getBusLinesListing().addListListener(new CustomListListener());
        view.getBusLinesListing().addSearchListenr(new SearchListener());
        view.getLoginArea().addLoginListener(new LoginListener());
    }
    private List<String> getStationNames(List<StationNode> stations){
        List<String> result=new ArrayList<>();
        for(StationNode node: stations){
            List<String> l = node.getBusLines();
            for(String s:l) {
                if (!result.contains(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
    class CustomListListener implements ListSelectionListener{
        @Override
        public void valueChanged(ListSelectionEvent e) {
            view.getBusLinesArea().setSelectedBus(view.getBusLinesListing().getList().getSelectedValue());
        }
    }
    class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            StationNode.Dijkstra(stationsPersistency.getAll(), view.getBusLinesListing().getF1().getText(),view.getBusLinesListing().getF2().getText());
        }
    }



    class LoginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<UserAccount> accountList=accountsPersistency.getAll();
            for(UserAccount account:accountList){
                if(account.getUsername().equals(view.getLoginArea().getUsrField().getText()) && account.getPswd().equals(view.getLoginArea().getPswField().getText())){
                    if(account.getType().equals("admin")) {
                        new AdminController(new AdminView(),accountsPersistency);
                    }else
                        new EmployeeController(view,new EmployeeView(stationsPersistency),stationsPersistency);
                    break;
                }
            }
        }
    }


}
