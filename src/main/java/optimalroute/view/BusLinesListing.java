package optimalroute.view;
import optimalroute.model.Busline;
import optimalroute.model.StationNode;
import optimalroute.model.persistency.Persistency;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BusLinesListing extends JPanel {
    private JList<String> list;
    Persistency persistency;
    List<StationNode> stations;
    private JLabel s1;
    private JLabel s2;
    private JTextField f1;
    private JTextField f2;
    private JButton search;
    public BusLinesListing(BusLinesArea bla,Persistency persistency){
        search=new JButton("Search");
        s1=new JLabel("Departure");
        s2=new JLabel("       Arrival");
        f1 = new JTextField(20);
        f2 = new JTextField(20);
        JPanel p1=new JPanel();
        JPanel p2=new JPanel();
        p1.add(s1);
        p1.add(f1);
        p2.add(s2);
        p2.add(f2);
        this.persistency = persistency;
        stations = persistency.getAll();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        DefaultListModel model = new DefaultListModel();
        list = new JList(model);
        list.setSelectedIndex(0);
        list.setSelectionMode(0);
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                bla.setSelectedBus(list.getSelectedValue());
            }
        });
        JScrollPane pane = new JScrollPane(list);
        this.add(pane);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        panel.add(search);

        this.add(panel);
        this.add(Box.createRigidArea(new Dimension(10,200)));
        for(String s:getStationNames()){
            model.addElement("Line : "+s);
        }
        this.setPreferredSize(new Dimension(100, 400));
    }
    public List<String> getStationNames(){
        List<String> result=new ArrayList<>();
        for(StationNode node: stations){
            List<String> l = node.getBusLines();
            for(String s:l) {
                if (!result.contains(s)) {
                    result.add(s);
                }
            }
        }
        return result;
    }
    public JList<String> getList() {
        return list;
    }
}
