package optimalroute.view;

import javax.swing.*;
import java.awt.*;

public class BusLinesListing extends JPanel {
    private JList<String> list;
    public BusLinesListing(){
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        JScrollPane pane = new JScrollPane(list);
        this.add(pane);
        for(int i=1;i<=30;i++)
            model.addElement("Line "+i);
        this.setPreferredSize(new Dimension(100, 400));
    }
}
