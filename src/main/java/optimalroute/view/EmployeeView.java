package optimalroute.view;

import optimalroute.model.persistency.Persistency;
import optimalroute.model.persistency.PersistencyFactory;

import javax.swing.*;

public class EmployeeView extends JFrame {
    public EmployeeView(){
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        PersistencyFactory factory=new PersistencyFactory();
        Persistency stationNodePersistency = factory.createPersistency("StationNode","stations.dat");
        MapArea map = new MapArea(stationNodePersistency);
        this.setContentPane(new DualView(map,new NodeTool(map.getData(), stationNodePersistency)));
        this.pack();
        this.setVisible(true);


    }
}
