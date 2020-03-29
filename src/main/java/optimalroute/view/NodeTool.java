package optimalroute.view;

import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NodeTool extends JPanel {

    private JButton addLink;
    private JButton add;
    private JButton rmv;
    private JButton save;
    public NodeTool(List<StationNode> data, Persistency persistency){
        addLink = new JButton("Add link");
        add = new JButton("Add node");
        rmv = new JButton("Remove node");
        save = new JButton("Save");
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(add);
        this.add(rmv);
        this.add(addLink);
        this.add(Box.createRigidArea(new Dimension(10,100)));
        this.add(save);
        addLink.addActionListener(new AddLinkListener());
        add.addActionListener(new AddListener());
        save.addActionListener(new SaveListener(data, persistency));

        rmv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapArea.toggleRmv();
            }
        });
    }
    public class AddLinkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MapArea.toggleLink();
        }
    }
    public class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            MapArea.toggleAdd();
        }
    }
    public class SaveListener implements ActionListener{
        Persistency persistency;
        List<StationNode> data;
        SaveListener(List<StationNode> data, Persistency persistency){
            this.persistency = persistency;
            this.data = data;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            persistency.add(data);
            MapArea.toggleSave();
        }
    }
}
