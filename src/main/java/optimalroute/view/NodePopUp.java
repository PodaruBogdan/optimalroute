package optimalroute.view;

import optimalroute.model.Coordinate;
import optimalroute.model.Station;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

public class NodePopUp extends JFrame{
    JTextField name;
    JLabel nameLabel;
    JButton add;
    private int x,y;
    List<StationNode> map;
    public NodePopUp(List<StationNode> map, int x, int y){
        this.map = map;
        this.x = x;
        this.y = y;
        name = new JTextField(10);
        nameLabel=new JLabel("Station name");
        add = new JButton("Set");
        this.setSize(new Dimension(40,40));
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(nameLabel);
        pane.add(name);
        pane.add(add);
        this.setLocation(new Point(x,y));
        this.setContentPane(pane);
        this.pack();
        this.setVisible(true);
        add.addActionListener(new MyCustomListener(this));
    }
    class MyCustomListener implements ActionListener{
        private JFrame frame;
        MyCustomListener(JFrame frame){
            this.frame = frame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.hide();
            Station station = new Station(name.getText());
            String uniqueID = UUID.randomUUID().toString();
            StationNode node = new StationNode(uniqueID, station, new Coordinate(x,y));
            map.add(node);
        }
    }

}
