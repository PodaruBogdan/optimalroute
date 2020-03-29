package optimalroute.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NodeTool extends JPanel {

    private JButton addLink;
    private JButton add;
    private JButton rmv;
    private JButton save;
    public NodeTool(){
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
    }
    public void AddAddListener(ActionListener listener){
        add.addActionListener(listener);
    }
    public void AddRemoveListener(ActionListener listener){
        rmv.addActionListener(listener);
    }
    public void AddSaveListener(ActionListener listener){
        save.addActionListener(listener);
    }
    public void AddAddLinkListener(ActionListener listener){
        addLink.addActionListener(listener);
    }
}
