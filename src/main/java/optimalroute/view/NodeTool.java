package optimalroute.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NodeTool extends JPanel {

    private JButton addLink;
    private JButton edit;
    private JButton add;
    private JButton rmv;
    public NodeTool(){
        addLink = new JButton("Add link");
        edit = new JButton("Edit node");
        add = new JButton("Add node");
        rmv = new JButton("Remove node");

        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(add);
        this.add(edit);
        this.add(rmv);
        this.add(addLink);
        addLink.addActionListener(new AddLinkListener());
        add.addActionListener(new AddListener());
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
}
