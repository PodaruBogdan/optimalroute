package optimalroute.view;

import optimalroute.model.persistency.StationNodePersistency;

import javax.swing.*;

public class EmployeeView extends JFrame {
    public EmployeeView(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new DualView(new MapArea(new StationNodePersistency("stations.dat")),new NodeTool()));
        this.pack();
        this.setVisible(true);


    }
}
