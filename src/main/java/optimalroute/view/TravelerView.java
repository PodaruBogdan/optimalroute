package optimalroute.view;
import optimalroute.model.User;
import optimalroute.model.UserAccount;
import optimalroute.model.persistency.Persistency;
import optimalroute.model.persistency.PersistencyFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

public class TravelerView extends JApplet {
    public static void main(String[] args) {
        JFrame window = new JFrame("Main screen");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        PersistencyFactory factory = new PersistencyFactory();
        Persistency stationPersistency = factory.createPersistency("StationNode","stations.dat");
        Persistency accountsPersistency = factory.createPersistency("Account","accounts.dat");

        String id = UUID.randomUUID().toString();
        User user=new User("admin","Podaru Bogdan");
        UserAccount admin = new UserAccount("bogdan",id,"1234","podaru.bogdan98@gmail.com",user);
        List<UserAccount> list= accountsPersistency.getAll();
        boolean found=false;
        for(UserAccount account:list){
            if(account.getUsername().equals(admin.getUsername())){
                found=true;
                break;
            }
        }
        if(found==false){
            list.add(admin);
            accountsPersistency.add(list);
        }



        BusLinesArea bla =new BusLinesArea(stationPersistency);
        BusLinesListing bls = new BusLinesListing(bla, stationPersistency);
        panel.add(bls);
        panel.add(Box.createRigidArea(new Dimension(20,80)));
        panel.add(new LoginArea(accountsPersistency));
        window.setContentPane(new DualView(bla, panel));
        window.pack();
        window.setVisible(true);
    }
}
