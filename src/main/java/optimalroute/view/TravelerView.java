package optimalroute.view;

import optimalroute.model.Coordinate;
import optimalroute.model.Station;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.StationNodePersistency;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class TravelerView extends JApplet {
    public static void main(String[] args) {
        JFrame window = new JFrame("Main screen");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new BusLinesListing());
        panel.add(Box.createRigidArea(new Dimension(20,80)));
        panel.add(new LoginArea());
        window.setContentPane(new DualView(new BusLinesArea(), panel));
        window.pack();
        window.setVisible(true);

        StationNodePersistency persistency = new StationNodePersistency("stations.dat");
        StationNode node1 = new StationNode(1,new Station("station1"),new Coordinate(30,30));
        StationNode node2 = new StationNode(2,new Station("station2"),new Coordinate(40,40));
        persistency.add(node1);
        persistency.add(node2);

        List<StationNode> stations = persistency.getAll();
        for(StationNode s:stations){
            System.out.println(s);
        }



    }
}
