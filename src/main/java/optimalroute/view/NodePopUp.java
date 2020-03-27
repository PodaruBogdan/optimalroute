package optimalroute.view;

import javax.swing.*;
import java.awt.*;

public class NodePopUp extends JFrame{
    JTextField name;
    JLabel nameLabel;
    JButton add;
    public NodePopUp(int x,int y){
        name = new JTextField(10);
        nameLabel=new JLabel("Station name");
        add = new JButton("Set");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane,BoxLayout.Y_AXIS));
        pane.add(nameLabel);
        pane.add(name);
        pane.add(add);
        this.setLocation(new Point(x,y));
        this.setContentPane(pane);
        //this.pack();
        this.setVisible(true);
    }
    public String getNameContent(){
        return name.getText();
    }

}
