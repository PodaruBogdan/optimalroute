package optimalroute.view;
import optimalroute.model.persistency.Persistency;
import optimalroute.model.persistency.PersistencyFactory;

import javax.swing.*;
import java.awt.*;

public class TravelerView extends JApplet {
    public static void main(String[] args) {
        JFrame window = new JFrame("Main screen");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        PersistencyFactory factory = new PersistencyFactory();
        Persistency stationPersistency = factory.createPersistency("StationNode","stations.dat");
        BusLinesArea bla =new BusLinesArea(stationPersistency);
        BusLinesListing bls = new BusLinesListing(bla, stationPersistency);
        panel.add(bls);
        panel.add(Box.createRigidArea(new Dimension(20,80)));
        panel.add(new LoginArea());
        window.setContentPane(new DualView(bla, panel));
        window.pack();
        window.setVisible(true);
    }
}
